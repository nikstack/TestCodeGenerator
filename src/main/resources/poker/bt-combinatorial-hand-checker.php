<?php


namespace PokerTest\Model\PokerGame;


use PHPUnit\Framework\TestCase;
use Poker\Model\Game\Item\Card\PlayingCard;
use Poker\Model\Game\Item\Card\PlayingCardRank;
use Poker\Model\Game\Item\Card\PlayingCardSuit;
use Poker\Model\PokerGame\CombinatorialHandChecker;
use Poker\Model\PokerGame\Hand;
use Poker\Model\PokerGame\HandType;

class AutoCombinatorialHandCheckerTest extends TestCase
{
    private CombinatorialHandChecker $checker;

    public function setUp(): void
    {
        $this->checker = new CombinatorialHandChecker();
    }

    #<tests>
}