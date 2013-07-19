#!/bin/sh
gcc -c -fPIC c_lib.c
gcc -shared c_lib.o -o libc_lib.so
