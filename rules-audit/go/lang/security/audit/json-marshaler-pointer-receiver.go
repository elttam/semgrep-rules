package tests

import "encoding/json"

// ----- Types used in tests -----

type SecretData struct {
	Token    string
	Password string
}

type PublicData struct {
	Name  string
	Score int
}

type NestedWrapper struct {
	Inner SecretData
}

// ----- Bad: pointer-receiver MarshalJSON — custom marshaller silently skipped
//           when the type is encoded by value -----

// ruleid: json-marshaler-pointer-receiver
func (s *SecretData) MarshalJSON() ([]byte, error) {
	return []byte(`{"token":"[REDACTED]"}`), nil
}

// ruleid: json-marshaler-pointer-receiver
func (w *NestedWrapper) MarshalJSON() ([]byte, error) {
	return json.Marshal(struct{ Inner string }{Inner: "[REDACTED]"})
}

// ----- Good: value-receiver MarshalJSON — always reachable regardless of how
//            the value is passed to json.Marshal -----

// ok: json-marshaler-pointer-receiver
func (p PublicData) MarshalJSON() ([]byte, error) {
	type Alias PublicData
	return json.Marshal(Alias(p))
}
