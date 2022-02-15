(ns alura.protocol.class1
  (:use [clojure pprint]))

(defn add-patient
  "Insert patient in patients list"
  [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient must have a id" patient))))

(defn test-add-patient
  "Test add-patient function"
  []
  (let [patients {}
        rogerio {:id 15 :name "Rogério" :birth "19/07/2002"}
        igor {:id 20 :name "Igor" :birth "18/06/2001"}
        gabriel {:id 25 :name "Gabriel" :birth "17/05/2000"}
        luciano {:name "Luciano" :birth "16/04/1999"}]
    (pprint (add-patient patients rogerio))
    (pprint (add-patient patients igor))
    (pprint (add-patient patients gabriel))
    (pprint (add-patient patients luciano))))

(defrecord Patient [id name birth])

(pprint (->Patient 15 "Rogério" "19/07/2002"))
(pprint (Patient. 15 "Rogério" "19/07/2002"))
(pprint (map->Patient {:id    15
                       :name  "Rogério"
                       :birth "19/07/2002"}))

(let [rogerio (->Patient 15 "Rogério" "19/07/2002")]
  (println (:id rogerio))
  (println (vals rogerio)))