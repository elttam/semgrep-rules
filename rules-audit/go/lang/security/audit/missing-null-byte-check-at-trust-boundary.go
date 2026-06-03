package tests

import (
	"errors"
	"os/exec"
	"strings"
)

// Bad: function compares username against a privileged literal and passes it
// to exec.Command without rejecting null bytes first.
// "admin\x00x" passes the == check in Go but resolves to "admin" in C/exec.
func badRunAsUser(username string) error {
	if username == "admin" {
		return errors.New("admin not allowed")
	}
	// ruleid: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--version")
	return cmd.Run()
}

// Bad: same pattern with "root" and exec.CommandContext.
func badRunAsRoot(ctx interface{ Done() <-chan struct{} }, username string) error {
	if username == "root" {
		return errors.New("root not allowed")
	}
	// ruleid: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--help")
	return cmd.Run()
}

// Bad: "superuser" literal — privileged regex still matches.
func badSuperuserExec(username string) error {
	if username == "superuser" {
		return errors.New("superuser not allowed")
	}
	// ruleid: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "status")
	return cmd.Run()
}

// Good: null-byte guard using strings.ContainsRune before the privileged check.
func goodRunAsUserContainsRune(username string) error {
	if strings.ContainsRune(username, 0) {
		return errors.New("invalid character in username")
	}
	if username == "admin" {
		return errors.New("admin not allowed")
	}
	// ok: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--version")
	return cmd.Run()
}

// Good: null-byte guard using strings.IndexByte != -1.
func goodRunAsUserIndexByte(username string) error {
	if strings.IndexByte(username, 0) != -1 {
		return errors.New("null byte in username")
	}
	if username == "root" {
		return errors.New("root not allowed")
	}
	// ok: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--status")
	return cmd.Run()
}

// Good: null-byte guard using strings.Contains with explicit \x00 literal.
func goodRunAsUserContainsNul(username string) error {
	if strings.Contains(username, "\x00") {
		return errors.New("null byte in username")
	}
	if username == "administrator" {
		return errors.New("administrator not allowed")
	}
	// ok: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--info")
	return cmd.Run()
}

// Good: guard using strings.IndexByte >= 0 (alternative comparison form).
func goodRunAsUserIndexByteGE(username string) error {
	if strings.IndexByte(username, 0) >= 0 {
		return errors.New("null byte detected")
	}
	if username == "admin" {
		return errors.New("admin not allowed")
	}
	// ok: missing-null-byte-check-at-trust-boundary
	cmd := exec.Command(username, "--check")
	return cmd.Run()
}
