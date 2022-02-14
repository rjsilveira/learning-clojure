(ns alura.concurrency.concurrency_logic)

(defn coming-in
  "Coming in"
  [hospital department person]
  (update hospital department conj person))

(defn attend
  "Attend a person"
  [hospital department]
  (update hospital department pop))