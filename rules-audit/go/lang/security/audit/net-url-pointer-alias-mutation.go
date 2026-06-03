package tests

import "net/url"

// Shared struct used across tests — simulates a server holding a *url.URL field.
type Server struct {
	serviceURL *url.URL
}

func (s *Server) copyURL() *url.URL {
	c := *s.serviceURL
	return &c
}

func mustParseURL(raw string) *url.URL {
	u, _ := url.Parse(raw)
	return u
}

// Bad: bare selector alias — $SRC is `s.serviceURL`, not excluded by any pattern-not.
// Writing to `u.RawQuery` mutates the shared *url.URL held by the server.
func badAliasRawQuery(s *Server) string {
	// ruleid: net-url-pointer-alias-mutation
	u := s.serviceURL
	u.RawQuery = "debug=1"
	return u.String()
}

// Bad: alias then mutate Path — still a bare selector source.
func badAliasPath(s *Server) string {
	// ruleid: net-url-pointer-alias-mutation
	u := s.serviceURL
	u.Path = "/v2"
	return u.String()
}

// Bad: bare variable alias (not a selector, still a bare identifier).
func badBareVarAlias(shared *url.URL) string {
	// ruleid: net-url-pointer-alias-mutation
	u := shared
	u.Host = "attacker.example.com"
	return u.String()
}

// Bad: alias then mutate Scheme.
func badAliasScheme(s *Server) string {
	// ruleid: net-url-pointer-alias-mutation
	u := s.serviceURL
	u.Scheme = "http"
	return u.String()
}

// Good: dereference copy — the write goes to a fresh copy, not the original.
func goodDereferenceCopy(s *Server) string {
	// ok: net-url-pointer-alias-mutation
	u := *s.serviceURL
	u.RawQuery = "debug=1"
	return u.String()
}

// Good: value composite literal — fresh url.URL struct, no aliasing.
func goodCompositeURLLiteral() string {
	// ok: net-url-pointer-alias-mutation
	u := url.URL{Scheme: "https", Host: "example.com", Path: "/api"}
	u.RawQuery = "v=1"
	return u.String()
}

// Good: pointer composite literal — also a fresh allocation.
func goodPointerCompositeURL() string {
	// ok: net-url-pointer-alias-mutation
	u := &url.URL{Scheme: "https", Host: "example.com"}
	u.RawQuery = "foo=bar"
	return u.String()
}

// Good: plain function call result — $F(...) is excluded.
func goodFunctionCallResult(raw string) string {
	// ok: net-url-pointer-alias-mutation
	u := mustParseURL(raw)
	u.RawQuery = "clean=1"
	return u.String()
}

// Good: method call result — $R.$M(...) is excluded (returns a new value).
func goodMethodCallResult(s *Server) string {
	// ok: net-url-pointer-alias-mutation
	u := s.copyURL()
	u.RawQuery = "debug=1"
	return u.String()
}
