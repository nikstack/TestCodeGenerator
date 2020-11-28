<?php


namespace PokerTest\Model\Poker;


use Class1;
use Class2;
use Class3;


class EvaluatorTest {

public function First() {
    $input = ['karo09','pik12','kreuz09','herz09','karo12','herz14','pik06'];
    $this->assertEquals('fullhouse', $evaluator->get($input));
}

public function Second() {
    $input = ['karo09','pik12','kreuz09','herz09','karo12','herz14','pik06'];
    $this->assertEquals('fullhouse', $evaluator->get($input));
}

public function Third() {
    $input = ['karo09','pik12','kreuz09','herz09','karo12','herz14','pik06'];
    $this->assertEquals('fullhouse', $evaluator->get($input));
}


}