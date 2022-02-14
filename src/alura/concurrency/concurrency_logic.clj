(ns alura.concurrency.concurrency_logic)

(defn full?
  "The queue is full?"
  [hospital department]
  (-> hospital
      (get,,, department)
      count,,,
      (<,,, 5)))

;; swap! my-hospital update :waiting-room conj "Renata"
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

(defn my-attend
  "My attend"
  [hospital department]
  (let [queue (get hospital department)
        functions (juxt peek pop)
        [patient-in-attendance other-patients] (functions queue)
        updated-hospital (update hospital assoc department other-patients)]
    {:patient-in-attendance patient-in-attendance
     :hospital              updated-hospital}))

(defn transfer
  "Transfer patient"
  [hospital from to]
  (let [person (peek (get hospital from))]
    (-> hospital
        (attend from)
        (coming-in to person))))