<?php

namespace PokerTest\Model\PokerGame;


use Poker\Model\Game\Item\Card\PlayingCard;
use Poker\Model\Game\Item\Card\PlayingCardRank;
use Poker\Model\Game\Item\Card\PlayingCardSuit;
use Poker\Model\PokerGame\CombinatorialHandChecker;
use PHPUnit\Framework\TestCase;
use Poker\Model\PokerGame\Hand;
use Poker\Model\PokerGame\HandType;


class MathematicalHandEvaluatorTest {
    private IHandEvaluator $evaluator;

    public function setup()
    {
        $this->evaluator = new MathematicalHandEvaluator();
    }

    public function testStdFullHouse() {
    $hand = new Hand([
                new PlayingCard(PlayingCardRank::$FIVE, PlayingCardSuit::$HEARTS),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$DIAMONDS),
                new PlayingCard(PlayingCardRank::$THREE, PlayingCardSuit::$DIAMONDS),
                new PlayingCard(PlayingCardRank::$FIVE, PlayingCardSuit::$SPADES),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$CLUBS),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$SPADES),
                new PlayingCard(PlayingCardRank::$QUEEN, PlayingCardSuit::$CLUBS),
            ]);
    $this->assertNotEquals(0, $this->evaluator->evaluate($hand));
    $this->assertNotEquals(HandType::$FULL_HOUSE, $hand->getType());
}

public function testAsdf() {
    $hand = new Hand([
                new PlayingCard(PlayingCardRank::$FIVE, PlayingCardSuit::$HEARTS),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$SPADES),
                new PlayingCard(PlayingCardRank::$THREE, PlayingCardSuit::$DIAMONDS),
                new PlayingCard(PlayingCardRank::$FIVE, PlayingCardSuit::$SPADES),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$CLUBS),
                new PlayingCard(PlayingCardRank::$KING, PlayingCardSuit::$SPADES),
                new PlayingCard(PlayingCardRank::$QUEEN, PlayingCardSuit::$CLUBS),
            ]);
    $this->assertNotEquals(0, $this->evaluator->evaluate($hand));
    $this->assertNotEquals(HandType::$FULL_HOUSE, $hand->getType());
}


}