(ns alura.protocol.class3
  (:use [clojure pprint])
  (:require [alura.protocol.logic :as a.p.logic]))

(defn load-patient
  "Load patient"
  [id]
  (println "Loading...")
  (Thread/sleep 1000)
  {:id id, :load-at (a.p.logic/now)})

(defn- load-if-not-exists
  "Load patient if not exists"
  [patients id loader]
  (if (contains? patients id)
    patients
    (let [patient (loader id)]
      (assoc patients id patient))))

;; (pprint (load-if-not-exists {} 15 load-patient))
;; (pprint (load-if-not-exists {15 {:id 15}} 15 load-patient))

(defprotocol Loadable
  (load! [this id]))

(defrecord Cache [cache loader]
  Loadable
  (load! [_ id]
    (swap! cache load-if-not-exists id loader)
    (get @cache id)))

(def patients (->Cache (atom {}), load-patient))
(pprint patients)
(load! patients 1)
(load! patients 2)
(load! patients 1)
(pprint patients)
