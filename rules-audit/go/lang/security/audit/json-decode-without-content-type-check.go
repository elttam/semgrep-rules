package tests

import (
	"encoding/json"
	"net/http"
)

type payload struct {
	Name  string
	Value int
}

// Bad: inline NewDecoder+Decode in a std handler with no Content-Type check.
func badHandlerInlineDecode(w http.ResponseWriter, r *http.Request) {
	var v payload
	// ruleid: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
}

// Bad: split NewDecoder + Decode, no Content-Type check.
func badHandlerSplitDecode(w http.ResponseWriter, r *http.Request) {
	var v payload
	dec := json.NewDecoder(r.Body)
	// ruleid: json-decode-without-content-type-check
	dec.Decode(&v)
}

// Bad: method-receiver handler, no Content-Type check.
type WriteHandler struct{}

func (h *WriteHandler) badMethodHandler(w http.ResponseWriter, r *http.Request) {
	var v payload
	// ruleid: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
}

// Bad: handler with extra parameters (non-standard signature), no Content-Type check.
type ctxKey struct{}

func badHandlerExtraParam(w http.ResponseWriter, r *http.Request, extra string) {
	var v payload
	// ruleid: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
}

// Good: handler validates Content-Type via r.Header.Get before decoding.
func goodHandlerContentTypeCheck(w http.ResponseWriter, r *http.Request) {
	ct := r.Header.Get("Content-Type")
	if ct != "application/json" {
		http.Error(w, "unsupported content type", http.StatusUnsupportedMediaType)
		return
	}
	var v payload
	// ok: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
}

// Good: method receiver validates Content-Type.
func (h *WriteHandler) goodMethodHandlerWithCheck(w http.ResponseWriter, r *http.Request) {
	r.Header.Get("Content-Type")
	var v payload
	// ok: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
}

// Good: split NewDecoder+Decode but Content-Type check is present.
func goodHandlerSplitDecodeWithCheck(w http.ResponseWriter, r *http.Request) {
	r.Header.Get("Content-Type")
	var v payload
	dec := json.NewDecoder(r.Body)
	// ok: json-decode-without-content-type-check
	dec.Decode(&v)
}

// Good: handler uses Header.Values instead of Header.Get — also suppresses the rule.
// Note: the rule's Header.Values pattern-not-inside requires a return type ($RET),
// so this function must declare one for the suppression to apply.
func goodHandlerContentTypeValues(w http.ResponseWriter, r *http.Request) error {
	_ = r.Header.Values("Content-Type")
	var v payload
	// ok: json-decode-without-content-type-check
	json.NewDecoder(r.Body).Decode(&v)
	return nil
}
