(ns gen-art.concentric-circles
  (:use [rosado.processing]
        [rosado.processing.applet]))

;; Listing 2.3
;; ===========

;; Page 37

;; int diam = 10;
;; float centX, centY;

;; void setup() {
;;   size(500, 300);
;;   frameRate(24);
;;   smooth();
;;   background(180);
;;   centX = width/2;
;;   centY = height/2;
;;   stroke(0);
;;   strokeWeight(1);
;;   noFill();
;; }

;; void draw() {
;;   if(diam <= 400) {
;;     ellipse(centX, centY, diam, diam);
;;     diam += 10;
;;   }
;; }

(defn setup []
  (size 500 300)
  (frame-rate 24)
  (smooth)
  (background 180)
  (stroke 0)
  (stroke-weight 1)
  (no-fill)
  (set-state! :diam (atom 10)
              :cent-x (/ (width) 2)
              :cent-y (/ (height) 2)))

(defn draw []
  (let [cent-x (state :cent-x)
        cent-y (state :cent-y)
        diam   (state :diam)]
    (when (<= @diam 400)
      (ellipse cent-x cent-y @diam @diam)
      (swap! diam + 10))))

(defapplet example
  :title "Concentric Circles"
  :setup setup
  :draw draw
  :size [500 300])

(run example :interactive)
;;(stop example)