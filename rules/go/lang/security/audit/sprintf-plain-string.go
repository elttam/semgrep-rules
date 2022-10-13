package main

import (
	"flag"
	"fmt"
	"path"
	"path/filepath"
)

func sformat(s string) string {
	sstring := fmt.Sprintf("s-formatted string: %s asdf", s)
	return sstring
}

func qformat(s string) string {
	sstring := fmt.Sprintf("s-formatted string: %q", s)
	return sstring
}


func main() {
	args := flag.String("string", "", "a test string")
	flag.Parse()

	root := "/"
	URL := "https://example.com/"
	inject := "adsf\u000a"


	// ruleid: sprintf-unescaped-control-characters
	fmt.Println("path.join test: ", path.Join(URL, sformat(*args)))
	// ruleid: sprintf-unescaped-control-characters
	fmt.Println("filepath.join test: ", filepath.Join(root, sformat(*args)))
	// ruleid: sprintf-unescaped-control-characters
	fmt.Println("path.join test: ", path.Join(URL, sformat(inject)))
	// ruleid: sprintf-unescaped-control-characters
	fmt.Println("filepath.join test: ", filepath.Join(root, sformat(inject)))

	fmt.Println("path.join test: ", path.Join(URL, qformat(*args)))
	fmt.Println("filepath.join test: ", filepath.Join(root, qformat(*args)))
	fmt.Println("path.join test: ", path.Join(URL, qformat(inject)))
	fmt.Println("filepath.join test: ", filepath.Join(root, qformat(inject)))

}
