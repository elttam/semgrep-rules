package main

import (
	"fmt"
	"log"
	"os"
	"path/filepath"
)

func withEval() string {
	// ok: potential-symlink-takeover-with-os.executable
	execBin, _ := os.Executable()
	result, err := filepath.EvalSymlinks(execBin)
	if err != nil {
		log.Fatalf("error %v\n", err)
	}

	path, err := filepath.Abs(filepath.Dir(result))
	if err != nil {
		log.Fatalf("error %v\n", err)
	}
	fmt.Println("Path with filepath.EvalSymlinks():", path)
	return path
}

func withoutEval() string {
	//ruleid: potential-symlink-takeover-with-os.executable
	execBin, _ := os.Executable()
	path, err := filepath.Abs(filepath.Dir(execBin))
	if err != nil {
		log.Fatalf("error %v\n", err)
	}
	fmt.Println("Path without filepath.EvalSymlinks():", path)
	return path
}

func printFile(path string) {
	fname := "test.txt"
	abspath := filepath.Join(path, fname)

	file, err := os.Open(abspath)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	fbytes := make([]byte, 16)
	bytesRead, err := file.Read(fbytes)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Number of bytes read: %d\n", bytesRead)
	fmt.Printf("Bytes: %s\n", fbytes)
}

func main() {
	goodpath := withEval()
	printFile(goodpath)

	badpath := withoutEval()
	printFile(badpath)
}
