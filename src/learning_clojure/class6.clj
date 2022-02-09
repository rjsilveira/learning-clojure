(ns learning-clojure.class6)

;(defn calculate-and-print-value-per-item
;  "Calculate and print total value per item"
;  [[key value]]
;  (let [total-value (calculate-value-per-item key value)]
;    (println "item:" key "value:" total-value)
;    total-value))

;(println (reduce + (map calculate-and-print-value-per-item request)))

(def request {:monitor  {:count 10, :price 119.99}
              :chair    {:count 5, :price 109.99}
              :desk     {:count 7, :price 99.99}
              :keyboard {:count 20 :price 19.99}
              :mouse    {:count 21 :price 15.99}})

(defn calculate-value-per-item
  "Receive a request item and return item total value "
  [[_ request-item]]
  (println request-item)
  (* (:count request-item) (:price request-item)))

(->> request
     (map calculate-value-per-item)
     (reduce +)
     (println))