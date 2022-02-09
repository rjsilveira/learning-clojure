(ns alura.class5)

(def stock {"Monitor"  10
            "Chair"    5
            "Desk"     7
            "Keyboard" 20
            "Mouse"    21})

(def stock-pattern {:monitor  10
                    :chair    5
                    :desk     7
                    :keyboard 20
                    :mouse    21})

(println stock)
(println (vals stock))
(println (keys stock))

(println (assoc stock-pattern :mouse-pad 20))
(println (update stock-pattern :monitor inc))

(def request {:monitor {:count 10, :price 119.99}
              :chair   {:count 5, :price 109.99}
              :desk    {:count 7, :price 99.99}})

(println request)
(println (:monitor request {}))
(println (:count (:monitor request {})))

(println (update-in request [:monitor :count] inc))

(println (-> request :monitor :count))

(-> request :monitor println)