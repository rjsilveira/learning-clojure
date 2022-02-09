(ns clojure.syntax)

; Comments

(def integer-value 42N)
(def long-value 42)
(def big-decimal-value 42.1M)
(def float-value 1.5)
(def ratio-value 22/7)
(def string-value "Hello")
(def character-value \e)
(def regular-expression-value #"[0-9]")

(println (class integer-value) integer-value)
(println (class long-value) long-value)
(println (class big-decimal-value) big-decimal-value)
(println (class float-value) float-value)
(println (class ratio-value) ratio-value)
(println (class string-value) string-value)
(println (class character-value) character-value)
(println (class regular-expression-value) regular-expression-value)

(println \newline "New line test")
(println \tab "Tab test")

(def null-symbol nil)
(println null-symbol)

; Symbols are accepted, but symbols already used prints a warning in console
; (def * "Symbols accept test")
; (println *)

(def list-value '(1 2 3))
(def vector-value [1 2 3])
(def set-value #(1 1 2 3))
(def map-value {:key-one "value one" :key-two "value two"})

(println (class list-value) list-value)
(println (class vector-value) vector-value)
(println (class set-value) set-value)
(println (class map-value) map-value)

; Sum
(println (+ 3 4 7))

; Standalone symbol
'x

; When used in console with REPL, *1 return the last result returned in console
(+ 1 1)
(println *1)

; (doc +)
; + is the function what you want to see the documentation

; (aprops "+")
; Search functions with +

; (find-doc "sum")]
; find functions with "sum" text

; (dir clojure.repl)
; List all functions in namespace clojure.repl

(print "Print" "without" "new line")
(print \newline)

(prn "Print as data with new line \n")
(println "Print with new line \n")

; Test your knowledge
; 1
(println (+ 7654 1234))

; 2
; ( 7 + 3 * 4 + 5 ) / 10
(println (format "%.2f" (double (/ (+ 7 (* 3 4) 5) 10))))

; 3
; (doc rem)
; (doc mod)

; 4
; (find-doc "stack trace")