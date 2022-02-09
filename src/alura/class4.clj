(ns alura.class4)

(def values [30
             70
             101
             500
             1000])

(println values)

(println (values 0))
(println (get values 3))

(defn plus
  "Increase value"
  [original-value]
  (+ original-value 1))

(def values (update values 0 plus))
(println values)

(def values (update values 0 #(- % 1)))
(println values)

(defn apply-discount?
  "Return if the original value is enabled for discount"
  [original-value]
  (> original-value 100))

(defn discounted-value
  "Return the discounted value if the value is enabled for that or
  else return the original value"
  [original-value]
  (if (apply-discount? original-value)
    (let [discount-tax 0.10
          discount (* original-value discount-tax)]
      (- original-value discount))
    original-value))

(println (map discounted-value values))

(println (filter apply-discount? values))

(defn sum
  "Sum two values"
  [arg1 arg2]
  (+ arg1 arg2))

(println (reduce sum values))

