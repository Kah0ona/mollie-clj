(ns mollie-clj.http
  (:require
    [clj-http.client :as client]
    [mollie-clj.core :refer [base-url]]
    [mollie-clj.config :refer [check-config-or-throw api-key]]))

(defn build-headers
  [params]
  (merge 
    {:authorization (str "Bearer " @api-key)} 
    params))

(defn build-params
  [params]
  {:as :json
   :coerce :always ;also coerce when error http status is returned
   :headers (build-headers params)})

(defn throw-err
  "Throws based on mollie response.
  Returns a map with :status, :message, :error (raw body, some error structure), :raw (complete response)"
  [r]
  (throw (ex-info
           {:status (:status r)
            :message (-> r :body :error :message)
            :error (:body r)
            :raw r})))

(defn GET [url params]
  (check-config-or-throw)
  (let [r (client/get url (build-params params))]
    (if (client/success? r)
      (:body r)
      (throw-err r))))

(defn POST 
  [url params]
  (check-config-or-throw)
  (let [r (client/post url (build-params params))]
    (if (client/success? r)
      (:body r)
      (throw-err r))))

(defn PUT
  [url params]
  (check-config-or-throw)
  (let [r (client/put url (build-params params))]
    (if (client/success? r)
      (:body r)
      (throw-err r))))

(defn DELETE
  [url params]
  (check-config-or-throw)
  (let [r (client/delete url (build-params params))]
    (if (client/success? r)
      (:body r)
      (throw-err r))))

(comment
  (clojure.pprint/pprint
    (client/get "http://google.com")))


