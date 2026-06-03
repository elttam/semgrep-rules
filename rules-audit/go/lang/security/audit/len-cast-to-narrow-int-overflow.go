package tests

import (
	"encoding/binary"
	"errors"
	"math"
)

// ============================================================
// Rule: len-cast-to-narrow-int-overflow  (INFO — broad audit)
// ============================================================

// Bad: unchecked narrowing cast of len() to uint32.
func badUncheckedUint32(data []byte) uint32 {
	// ruleid: len-cast-to-narrow-int-overflow
	return uint32(len(data))
}

// Bad: unchecked cast to int32.
func badUncheckedInt32(data []byte) int32 {
	// ruleid: len-cast-to-narrow-int-overflow
	return int32(len(data))
}

// Bad: unchecked cast to uint16.
func badUncheckedUint16(data []byte) uint16 {
	// ruleid: len-cast-to-narrow-int-overflow
	return uint16(len(data))
}

// Bad: unchecked cast to uint8 — tiny bound, easy overflow.
func badUncheckedUint8(s string) uint8 {
	// ruleid: len-cast-to-narrow-int-overflow
	return uint8(len(s))
}

// Good: len checked with > MAX before casting.
func goodCheckedGreaterThan(data []byte) (uint32, error) {
	if len(data) > math.MaxUint32 {
		return 0, errors.New("payload too large")
	}
	// ok: len-cast-to-narrow-int-overflow
	return uint32(len(data)), nil
}

// Good: len checked with <= MAX before casting (inverted guard style).
func goodCheckedLessOrEqual(data []byte) (uint32, error) {
	if len(data) <= math.MaxUint32 {
		// ok: len-cast-to-narrow-int-overflow
		return uint32(len(data)), nil
	}
	return 0, errors.New("payload too large")
}

// Good: guard uses intermediate variable form.
func goodCheckedIntermediateVar(data []byte) (uint32, error) {
	if n := len(data); n > math.MaxUint32 {
		return 0, errors.New("payload too large")
	}
	// ok: len-cast-to-narrow-int-overflow
	return uint32(len(data)), nil
}

// ============================================================
// Rule: len-cast-into-binary-length-prefix  (WARNING — high signal)
// ============================================================

// Bad: uint32 length prefix written to binary framing without bounds check.
// Both rules fire: the inner uint32(len()) cast triggers len-cast-to-narrow-int-overflow,
// and the full PutUint32 call triggers len-cast-into-binary-length-prefix.
func badBinaryLengthPrefixPutUint32(buf []byte, data []byte) {
	// ruleid: len-cast-to-narrow-int-overflow, len-cast-into-binary-length-prefix
	binary.LittleEndian.PutUint32(buf, uint32(len(data)))
	copy(buf[4:], data)
}

// Bad: uint16 length prefix — even smaller cap, easier to trigger.
func badBinaryLengthPrefixPutUint16(buf []byte, data []byte) {
	// ruleid: len-cast-to-narrow-int-overflow, len-cast-into-binary-length-prefix
	binary.BigEndian.PutUint16(buf, uint16(len(data)))
	copy(buf[2:], data)
}

// Good: bounds check before writing the length prefix.
func goodBinaryLengthPrefixChecked(buf []byte, data []byte) error {
	if len(data) > math.MaxUint32 {
		return errors.New("frame too large")
	}
	// ok: len-cast-into-binary-length-prefix
	binary.LittleEndian.PutUint32(buf, uint32(len(data)))
	copy(buf[4:], data)
	return nil
}

// Good: upper-bound check (inverted guard).
func goodBinaryLengthPrefixInvertedCheck(buf []byte, data []byte) error {
	if len(data) <= math.MaxUint16 {
		// ok: len-cast-into-binary-length-prefix
		binary.BigEndian.PutUint16(buf, uint16(len(data)))
		copy(buf[2:], data)
		return nil
	}
	return errors.New("frame exceeds uint16 max")
}
