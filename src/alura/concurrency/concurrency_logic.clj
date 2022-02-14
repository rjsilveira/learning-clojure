(ns alura.concurrency.concurrency_logic)

(defn full?
  "The queue is full?"
  [hospital department]
  (-> hospital
      (get,,, department)
      count,,,
      (<,,, 5)))

(defn coming-in
  "Coming in"
  [hospital department person]
  (if (full? hospital department)
    (update hospital department conj person)
    (throw (ex-info "Queue is full!" {:person person}))))

(defn attend
  "Attend a person"
  [hospital department]
  (update hospital department pop))