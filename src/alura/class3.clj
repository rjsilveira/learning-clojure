(ns alura.class3)

(defn apply-discount?
  "Return if the original value is enabled for discount"
  [original-value]
  (> original-value 100))

;(println
;  (apply-discount? 101))
;
;(println
;  (apply-discount? 100))
;
;(println
;  (apply-discount? 99))

(defn discounted-value
  "Return the discounted value if the value is enabled for that or else return the original value"
  [apply-discount? original-value]
  (if (apply-discount? original-value)
    (let [discount-tax 0.10
          discount (* original-value discount-tax)]
      (- original-value discount))
    original-value))

(println
  (discounted-value #(> % 100) 100))

(println
  (discounted-value #(> % 100) 1000))

