(ns alura.test.logic)

(defn can-join-in-the-queue?
  "The patient can join in the queue?"
  [hospital department]
  (some-> hospital
          department
          count
          (< 5)))

(defn coming-in
  "docstring"
  [hospital department patient]
  (if (can-join-in-the-queue? hospital department)
    (update hospital department conj patient)
    (throw (ex-info "Patient can't join into queue" {:patient patient :error :queue-is-full}))))

(defn attend
  "Attend a patient"
  [hospital department]
  (update hospital department pop))

(defn next-to
  "Next"
  [hospital department]
  (-> hospital
      department
      peek))

(defn transfer
  "Transfer"
  [hospital from to]
  (let [patient (next-to hospital from)]
    (-> hospital
        (attend from)
        (coming-in to patient))))

