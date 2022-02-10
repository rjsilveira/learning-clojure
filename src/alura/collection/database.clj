(ns alura.collection.database)

(def request-01
  {:user  15
   :items {:chair     {:id    :chair
                       :count 1
                       :price 119.99}
           :desk      {:id    :desk
                       :count 1
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 1
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 1}}})

(def request-02
  {:user  10
   :items {:chair     {:id    :chair
                       :count 1
                       :price 119.99}
           :desk      {:id    :desk
                       :count 1
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 1
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 1}}})

(def request-03
  {:user  27
   :items {:chair     {:id    :chair
                       :count 2
                       :price 119.99}
           :desk      {:id    :desk
                       :count 2
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 1
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 2}}})

(def request-04
  {:user  15
   :items {:chair     {:id    :chair
                       :count 1
                       :price 119.99}
           :desk      {:id    :desk
                       :count 1
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 1
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 5}}})

(def request-05
  {:user  19
   :items {:chair     {:id    :chair
                       :count 1
                       :price 119.99}
           :desk      {:id    :desk
                       :count 1
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 2
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 10}}})

(def request-06
  {:user  20
   :items {:chair     {:id    :chair
                       :count 1
                       :price 119.99}
           :desk      {:id    :desk
                       :count 1
                       :price 139.99}
           :monitor   {:id    :monitor
                       :count 1
                       :price 99.99}
           :cable-usb {:id    :cable-usb
                       :count 1}}})

(defn find-all
  "Find all requests"
  []
  [request-01
   request-02
   request-03
   request-04
   request-05
   request-06])
