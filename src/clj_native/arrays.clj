(ns clj-native.arrays
  (:refer-clojure :exclude [make-array])
  (:import [com.sun.jna Pointer Memory]))

(defn ptr-array
  "Creates a pointer to an array of pointers in native
  memory that may be passed to functions taking a jna
  Pointer (void*) as an argument."
  [null-terminate? & pointers]
  (let [n (inc (count pointers))
        size (* Pointer/SIZE n)
        mem #^Pointer (Memory. size)
        ptrvec (conj (vec pointers) (Pointer. 0))]
    (.write mem (long 0) #^"[Lcom.sun.jna.Pointer;"
            (into-array Pointer ptrvec) (int 0) (int n))
    mem))

