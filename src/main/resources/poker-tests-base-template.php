<?php

namespace PokerTest\Model\PokerGame;


use PHPUnit\Framework\TestCase;
use Poker\Model\Game\Item\Card\PlayingCard;
use Poker\Model\Game\Item\Card\PlayingCardRank;
use Poker\Model\Game\Item\Card\PlayingCardSuit;
use Poker\Model\PokerGame\Hand;
use Poker\Model\PokerGame\HandType;
use Poker\Model\PokerGame\IHandEvaluator;
use Poker\Model\PokerGame\MathematicalHandEvaluator;


class AutoMathematicalHandEvaluatorTest extends TestCase
{
    private IHandEvaluator $evaluator;

    public function setUp(): void
    {
        $this->evaluator = new MathematicalHandEvaluator();
    }

    #<tests>
}