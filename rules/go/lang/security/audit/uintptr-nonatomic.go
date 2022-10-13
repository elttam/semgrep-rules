package main

import (
	"syscall"
	"unsafe"
)

func main() {
	// ruleid: non-atomic-use-of-converted-uintptr
	argp := uintptr(unsafe.Pointer(attrp))
	_, _, errno := syscall.Syscall(syscall.SYS_IOCTL, f.Fd(), request, argp)
	if errno != 0 {
		panic(errno)
	}

	// ok: non-atomic-use-of-converted-uintptr
	ret, _, _ := syscall.Syscall(uintptr(globalMemoryStatusEx), 1, uintptr(unsafe.Pointer(&memoryStatusEx[0])), 0, 0)
	if ret == 0 {
		return 0
	}

}
