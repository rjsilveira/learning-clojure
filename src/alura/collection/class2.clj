(ns alura.collection.class2)

(def names
  ["Raphael"
   "Maria"
   "John"
   "Andrew"
   "Paul"
   "Tobey"
   "Rose"])

(defn my-reduce
  "Rewrite reduce function"
  ([sequence] (my-reduce sequence 0))
  ([sequence count]
  (println count sequence)
  (if (first sequence)
    (recur (rest sequence) (inc count))
    count)))

(println (my-reduce names))

(println (my-reduce []))

(defn my-loop
  "Example using loop"
  [sequence]
  (loop [count 0 rest-items sequence]
    (if (first rest-items)
      (recur (inc count) (rest rest-items))
      count)))

(println "Count using loop" (my-loop names))