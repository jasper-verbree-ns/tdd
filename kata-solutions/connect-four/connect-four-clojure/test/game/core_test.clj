(ns game.core-test
  (:require [clojure.test :refer [deftest is]]
            [game.utilities :refer [game-with-moves]]
            [game.board :refer [GAME can-play?]]
            [game.core :refer [generate-ai-move-for min-max]]
            [game.printer :refer [print-game]]))

(deftest given-full-column-cannot-play-full-column
  (is (= false ((can-play? 3) (game-with-moves [2 3 3 2 2 3 3 2 2 3 3]))))
  (is ((can-play? 2) (game-with-moves [2 3 3 2 2 3 3 2 2 3 3]))))

(deftest score-zero-for-a-draw
  (is (= 0 (min-max (game-with-moves [0 0 0 0 0 0 1 1 1 1 1 1 2 2 2 2 2 2 4 3 3 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 6 6 6 6 6 6]) 1))))

(deftest score-board-with-red-connect-four
  (is (= -18 (min-max (game-with-moves [0 1 0 1 0 1 0]) 1))))

(deftest score-board-with-yellow-connect-four
  (is (= 17 (min-max (game-with-moves [0 1 0 1 0 1 3 1]) 1))))

(deftest score-undecided-board
  (is (= 0 (min-max (game-with-moves [0 6 1 5 0 6]) 1))))

(deftest score-board-where-red-next-move-is-winning
  (is (= -18 (min-max (game-with-moves [0 1 0 1 0 1]) 1))))

(deftest score-board-where-yellow-next-move-is-winning
  ;(testing (print-game (game-with-moves [0 6 0 5 1 4 1])))
  (is (= 17 (min-max (game-with-moves [0 6 0 5 1 4 1]) 1))))

(deftest score-board-where-red-move-after-yellow-move-can-be-winning
  (is (= -18 (min-max (game-with-moves [3 3 2 2 1]) 2))))

(deftest suggested-move-on-board-where-yellow-next-move-is-winning)
  (is (= 3 (generate-ai-move-for (game-with-moves [0 6 0 5 1 4 1]))))

(deftest suggested-move-on-board-where-winning-opponent-move-is-possible)
  ;(testing (print-game (game-with-moves [5 1 5 2 5])))
  (is (= 5 (generate-ai-move-for (game-with-moves [5 1 5 2 5]))))

(deftest score-board-where-either-move-for-yellow-results-in-red-winning
  ;(testing (print-game (game-with-moves [3 6 4 4 2])))
  (is (= -18 (min-max (game-with-moves [3 6 4 4 2]) 2))))

