package tests

import (
	"net/http"

	"github.com/gorilla/sessions"
)

var secretKey = []byte("very-secret-key-32-bytes-minimum")

// Bad: NewCookieStore with no SameSite override — default is SameSiteNone.
func badCookieStoreNoOverride() *sessions.CookieStore {
	// ruleid: gorilla-cookiestore-default-samesite-none
	store := sessions.NewCookieStore(secretKey)
	return store
}

// Bad: NewFilesystemStore with no SameSite override.
func badFilesystemStoreNoOverride() *sessions.FilesystemStore {
	// ruleid: gorilla-cookiestore-default-samesite-none
	store := sessions.NewFilesystemStore("/tmp/sessions", secretKey)
	return store
}

// Bad: assignment (=) form — no SameSite set on globalStore afterwards.
var globalStore *sessions.CookieStore

func badCookieStoreAssignForm() {
	// ruleid: gorilla-cookiestore-default-samesite-none
	globalStore = sessions.NewCookieStore(secretKey)
}

// Good: SameSite set to Lax after NewCookieStore.
func goodCookieStoreSameSiteLax() *sessions.CookieStore {
	// ok: gorilla-cookiestore-default-samesite-none
	store := sessions.NewCookieStore(secretKey)
	store.Options.SameSite = http.SameSiteLaxMode
	return store
}

// Good: SameSite set to Strict.
func goodCookieStoreSameSiteStrict() *sessions.CookieStore {
	// ok: gorilla-cookiestore-default-samesite-none
	store := sessions.NewCookieStore(secretKey)
	store.Options.SameSite = http.SameSiteStrictMode
	return store
}

// Good: Options struct replaced entirely (guarantees SameSite is set explicitly).
func goodCookieStoreOptionsReplaced() *sessions.CookieStore {
	// ok: gorilla-cookiestore-default-samesite-none
	store := sessions.NewCookieStore(secretKey)
	store.Options = &sessions.Options{SameSite: http.SameSiteLaxMode}
	return store
}

// Good: FilesystemStore with SameSite set explicitly.
func goodFilesystemStoreSameSiteLax() *sessions.FilesystemStore {
	// ok: gorilla-cookiestore-default-samesite-none
	store := sessions.NewFilesystemStore("/tmp/sessions", secretKey)
	store.Options.SameSite = http.SameSiteLaxMode
	return store
}

// Good: FilesystemStore with Options replaced entirely.
func goodFilesystemStoreOptionsReplaced() *sessions.FilesystemStore {
	// ok: gorilla-cookiestore-default-samesite-none
	store := sessions.NewFilesystemStore("/tmp/sessions", secretKey)
	store.Options = &sessions.Options{SameSite: http.SameSiteStrictMode}
	return store
}
