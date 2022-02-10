(ns alura.collection.class3
  (:require [alura.collection.database :as a.c.database]))

(println (a.c.database/find-all))

(defn my-grouping-function
  "Function parameter to group requests by user"
  [item]
  (println "Item:" item)
  (:user item))

(def grouped-requests (group-by my-grouping-function (a.c.database/find-all)))

(println grouped-requests)
(println (map count (vals grouped-requests)))

(println "---------------------------------")

(defn calculate-item-total-value
  "Calculate item total value"
  [item]
  (* (get item :count 0) (get item :price 0)))

(defn calculate-request-total-value
  "Calculate request total value"
  [items]
  (->> items
       vals
       (map calculate-item-total-value)
       (reduce +)))

(defn calculate-total-value
  "Calculate request value by user"
  [request]
  (->> request
       (map :items)
       (map calculate-request-total-value)
       (reduce +)))

(defn count-request-by-user
  "Count requests by user"
  [[user request]]
  {:user        user
   :count       (count request)
   :total-value (calculate-total-value request)})

(->> (a.c.database/find-all)
     (group-by :user)
     (map count-request-by-user)
     println)

