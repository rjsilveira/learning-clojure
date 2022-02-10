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

(defn calculate-request-value
  "Calculate request value by user"
  [request]
  (15))

(defn count-request-by-user
  "Count requests by user"
  [[user request]]
  ({:user  user
    :count (count request)
    :total-value (calculate-request-value request)}))

(->> (a.c.database/find-all)
     (group-by :user)
     println
     (map count-request-by-user)
     println)

