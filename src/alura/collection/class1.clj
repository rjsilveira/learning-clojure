(ns alura.collection.class1)

(def names
  ["Raphael"
   "Maria"
   "John"
   "Andrew"])

(defn print-all-values
  "Print all values inside a vector"
  [vector]
  (println (first vector))
  (println (next vector)))

(print-all-values names)

(defn my-map
  "Rewrite map function"
  [function sequence]
  (if (first sequence)
    (let [first-item (first sequence)]
      (function first-item)
      (recur function (rest sequence)))))

(defn my-function
  "Function to print some value"
  [value]
  (println value))

(my-map my-function (range 10))