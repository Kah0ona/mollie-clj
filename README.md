# mollie-clj

mollie-clj is a Mollie API client written in clojure. It is a thin wrapper that abstracts away the
juggling with API keys and headers. The responses are untouched clj-http responses, so there's no
coercion into some datastructure, but that's probably not necessary when using clojure, and we can keep
things simple.

## Usage

Add 

```clojure
[mollie-clj "0.1.0"]
```

 to your project.clj

The function calls mimic the methods described in the Mollie documentation. And since in clojure we work with data preferably,
just mimic the structures you see in the docs and Bob's your uncle.

Make a call to (set-api-key! "yourmolliekey") to let the library use this key, without the need
to pass it in all the time.

Then, if you have a Ring app you need to configure routes to handle the callback of the Mollie payment, which
is called the webhook in Mollie terminology. They push info to an url that you configure in your Mollie profile. 

Example route for the callback can be like this:

```clojure
(POST "/mollie-callback" [payment-id] (my-mollie-webhook-handler payment-id))
```

You then want to probably do a get request to get payments with the above id:

```clojure
(ns my-app.my-namespace
  (:require 
   [ring.util.http-response :refer [ok]]
   [mollie-clj.core :as mollie]
   [mollie-clj.payments :as payments]))
    
(mollie/set-api-key! "myapikey")

(defn my-mollie-webhook-handler
  [id]
  (let [payment (payments/get-payment {:id id})]
    ; do something with payment, ie. mark something as paid in your database
    ; ...

    ;return ring ok, 200 http status, as per the docs (otherwise mollie will retry this a few times)
    (ok)))
```

## License

Released under the MIT License: http://www.opensource.org/licenses/mit-license.php
