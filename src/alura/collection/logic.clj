(ns alura.collection.logic)

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

(defn resume-by-user
  "Resume requests grouped by users"
  [requests]
  (->> requests
       (group-by :user)
       (map count-request-by-user)))