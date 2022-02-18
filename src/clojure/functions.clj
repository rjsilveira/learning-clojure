(ns clojure.functions
  (:import (java.net URL)))

(defn greet
  "Greet function"
  [name]
  (str "Hello, " name))

(println (greet "Test"))

; Function with two "arities"
(defn multiple-arity-function
  "Multiple arity function"
  ([] (multiple-arity-function "Hello word"))
  ([message] (println message)))

(println (multiple-arity-function))

; Variadic functions
(defn variadic-function
  "Variadic function"
  [single-parameter & variadic-parameter]
  (println "Single parameter:" single-parameter)
  (println "Variadic parameter:" variadic-parameter))

(println (variadic-function "SINGLE PARAMETER" "VARIADIC PARAMETER 1" "VARIADIC PARAMETER 2" "VARIADIC PARAMETER 3"))


; Anonymous functions
((fn [message] (println message)) "Hello word in a anonymous function!")

(def hello-word-function-variable (fn [message] (println message)))
(println (hello-word-function-variable "Anonymous function used as a variable"))

(#(println "Compressed anonymous function example: " %) "Hello word!")
(#(println "Compressed anonymous function example: " %1 %2) "Hello word!" "Hello word 2!")
(#(println "Compressed anonymous function example with variadic: " %1 %2 %&) "Hello word!" "Hello word 2!" "VARIADIC 1")

; apply function

(defn my-print
  "Rewrite print function"
  [& arg]
  (->> arg
       println))

(defn my-print2
  "Rewrite print function"
  [arg1 arg2 arg3]
  (println "arg1" arg1)
  (println "arg2" arg2)
  (println "arg3" arg3))

(defn my-apply-function
  "Example function with apply"
  [single-element list-element]
  (apply my-print single-element list-element)
  (apply my-print2 single-element list-element))

(my-apply-function "Hello" ["One", "Two"])

; let usages
(defn my-let-function
  "Function with let implementation"
  []
  (let [value-to-print "Value 1 to print"]
    (println value-to-print)))

(defn my-second-let-function
  "Function with let implementation"
  []
  (let [value-to-print "Value 2 to print"]
    (println value-to-print)))

(my-let-function)
(my-second-let-function)

; Closures
(defn my-closure-function
  "A simple closure function"
  [message]
  #(println message %))

(def call-closure-function (my-closure-function "Hello!"))
(call-closure-function "Hello again?")

; Java Interop
(println (.length "Hello"))

; Test your knowledge
; 1
(defn print-hello
  "Print hello"
  []
  (println "Hello 1"))
(print-hello)

; 2
(def print-hello-variable-1 (fn [] (println "Hello 2")))
(def print-hello-variable-2 #(println "Hello 3"))
(print-hello-variable-1)
(print-hello-variable-2)

; 3
(defn greeting
  "Greeting function"
  ([] "Hello, World!")
  ([arg] (str "Hello, " arg "!"))
  ([arg1 arg2] (str arg1 ", " arg2 "!")))

;; For testing
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))

; 4
(defn do-nothing
  "Do nothing"
  [arg]
  arg)

(println (do-nothing "Hello, word!"))

; 5
(defn always-thing
  "Always thing"
  [& _]
  100)

(println (always-thing 1 2 3 4))

; 6
(defn make-thingy [x] (fn [& _] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))

constantly
identity

; 7
(defn simple-print
  "Simple print"
  []
  (println "Simple print"))

(defn triplicate
  "Triplicate"
  [function]
  (function)
  (function)
  (function))

(triplicate simple-print)

; 8
(println "-------------------------------------------")
(defn my-true-function
  "Returns true"
  [& is?]
  is?)

(defn opposite
  "Opposite"
  [function]
  (fn [& args] (not (apply function args))))

(def call-opposite-function (opposite my-true-function))
(println (call-opposite-function true false true))

;; 9
(defn triplicate2
  "Triplicate 2"
  [function & args]
  (triplicate #(apply function args)))

;; 10
(println (Math/cos Math/PI))

(println (+ (Math/pow (Math/sin 0.2) 2)
            (Math/pow (Math/cos 0.2) 2)))

;; 11
(defn http-get [url]
  (slurp
    (.openStream
      (URL. url))))

(println (http-get "https://www.google.com/"))

;; 12
(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

;; 13
(defn two-fns [f g]
  (fn [x] (f (g x))))