(ns gen-art.custom-noise-circle
    (:use [rosado.processing]
          [rosado.processing.applet]
          [gen-art.util :only [range-incl line-join-points]]))

;; Listing 4.5, page 73

;; void setup(){
;;   size(500, 300);
;;   background(255);
;;   strokeWeight(5);
;;   smooth();

;;   float radius = 100;
;;   int centX = 250;
;;   int centY = 150;

;;   stroke(0, 30);
;;   noFill();
;;   ellipse(centX, centY, radius*2, radius*2);

;;   stroke(20, 50, 70);
;;   strokeWeight(1);
;;   float x, y;
;;   float noiseval = random(10);
;;   float radVariance, thisRadius, rad;
;;   beginShape();
;;   fill(20, 50, 70, 50);
;;   for(float ang = 0; ang <= 360; ang += 1){

;;     noiseval += 0.1;
;;     radVariance = 30 * customNoise(noiseval);

;;     thisRadius = radius + radVariance;
;;     rad = radians(ang);
;;     x = centX + (thisRadius * cos(rad));
;;     y = centY + (thisRadius * sin(rad));

;;     curveVertex(x, y);
;;   }
;;   endShape();
;; }

;; float customNoise(float value){
;;   float retValue = pow(sin(value), 3);
;;   return retValue;
;; }

(defn custom-noise [val]
  (pow (sin val) 3))

(defn setup []
  (size 500 300)
  (background 255)
  (stroke-weight 5)
  (smooth)
  (stroke 0 30)
  (no-fill)

  (let [radius     100
        cent-x     250
        cent-y     150
        noise-val  (rand 10)
        angles     (range-incl 0 360)
        rads       (map radians angles)
        noise-vals (range noise-val Float/POSITIVE_INFINITY 0.1)
        rad-vars   (map #(* 30 (custom-noise %)) noise-vals)
        radii      (map + rad-vars (repeat radius))
        xs         (map (fn [radius rad] (+ cent-x (* radius (cos rad)))) radii rads)
        ys         (map (fn [radius rad] (+ cent-y (* radius (sin rad)))) radii rads)]

    (ellipse cent-x cent-y (* 2 radius) (* 2 radius))
    (stroke 20 50 70)
    (stroke-weight 1)
    (begin-shape)
    (fill 20 50 70 50)
    (dorun (map curve-vertex xs ys))
    (end-shape)))

(defapplet example
  :title "Custom Noise Circle"
  :setup setup
  :size [500 300])

(run example :interactive)
;;(stop example)