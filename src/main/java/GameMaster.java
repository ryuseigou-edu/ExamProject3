import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;

import creature.*;
import creature.Character;
import creature.character.*;
import creature.monster.*;

public class GameMaster {
    private static int matangoCnt = 0;
    private static int goblinCnt = 0;
    private static int slimeCnt = 0;
    private static ArrayList<Character> party = new ArrayList<>();
    private static ArrayList<Monster> monsters = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        //party init
        Hero hero = new Hero("勇者", 100);
        Wizard wizard = new Wizard("魔法使い", 60, 10);
        Thief thief = new Thief("盗賊", 70);
        party.add(hero);
        party.add(wizard);
        party.add(thief);

        //monsters init
        for (int i = 0; i < 5; i++) {
            monsters.add(choiceEnemy());
        }

        System.out.println("---味方パーティー---");
        party.forEach(Character::showStatus);
        System.out.println("---敵グループ---");
        monsters.forEach(Monster::showStatus);
        br();



        do {
            System.out.println("味方の総攻撃！");
            br();
            Iterator<Character> itChar = party.iterator();
            while (itChar.hasNext()) {
                Character curChar = itChar.next();
                Monster curTar = monsters.getFirst();
                System.out.println("<<<["+curChar.getName()+"]のターン>>>");
                int choiceAction;
                if (curChar instanceof SuperHero curSuperHero) {
                    curTar = choiceTarget(curChar);
                    curSuperHero.attack(curTar);
                } else if(curChar instanceof Hero curHero) {
                    do {
                        System.out.println("1: 攻撃");
                        System.out.println("2: スーパーヒーローに進化！");
                        choiceAction = readInt("行動を選択するドン！(半角数字で頼む) >> ");
                        switch (choiceAction) {
                            case 1:
                                curTar = choiceTarget(curChar);
                                curHero.attack(curTar);
                                break;
                            case 2:
                                SuperHero curSuperHero = evoHero(curHero);
                                if(!curSuperHero.isAlive()) {
                                    System.out.print("が、");
                                    curSuperHero.die();
                                    System.out.print("www");
                                    itChar.remove();
                                }
                                break;
                            default:
                                System.err.println("選択肢からで頼む。");
                                Thread.sleep(100);
                        }
                    } while (!(choiceAction == 1) && !(choiceAction == 2));
                } else if (curChar instanceof Thief curThief) {
                    do {
                        System.out.println("1: 攻撃");
                        System.out.println("2: 守り");
                        choiceAction = readInt("["+curThief.getName()+"]の行動を選択するドン！(半角数字で頼む) >> ");
                        switch (choiceAction) {
                            case 1:
                                curTar = choiceTarget(curChar);
                                curThief.attack(curTar);
                                break;
                            case 2:
                                curThief.guard();
                                break;
                            default:
                                System.err.println("選択肢からで頼む。");
                                Thread.sleep(100);
                        }
                    } while (!(choiceAction == 1) && !(choiceAction == 2));
                } else if (curChar instanceof Wizard curWizard) {
                    do {
                        System.out.println("1: 攻撃");
                        System.out.println("2: 魔法攻撃");
                        choiceAction = readInt("["+curWizard.getName()+"]の行動を選択するドン！(半角数字で頼む) >> ");
                        switch (choiceAction) {
                            case 1:
                                curTar = choiceTarget(curChar);
                                curWizard.attack(curTar);
                                break;
                            case 2:
                                curTar = choiceTarget(curChar);
                                curWizard.magic(curTar);
                                break;
                            default:
                                System.err.println("選択肢からで頼む。");
                                Thread.sleep(100);
                        }
                    } while (!(choiceAction == 1) && !(choiceAction == 2));
                }
                if (!curTar.isAlive()) {
                    curTar.die();
                    monsters.remove(curTar);
                } else if (curTar.getHp() <= 5) {
                    curTar.run();
                    monsters.remove(curTar);
                }
                if (monsters.isEmpty()) {
                    break;
                }
                br();
            }
            if (monsters.isEmpty()) {
                break;
            }

            Iterator<Monster> itMon = monsters.iterator();
            System.out.println("<<<敵の総攻撃！>>>");
            while (itMon.hasNext()) {
                Monster curMon = itMon.next();
                Character curTar = party.get((int) (Math.random() * party.size()));
                if (curMon instanceof Matango curMatango) {
                    curMatango.attack(curTar);
                } else if (curMon instanceof Goblin curGoblin) {
                    curGoblin.attack(curTar);
                } else if (curMon instanceof Slime curSlime) {
                    curSlime.attack(curTar);
                }
                if (!curTar.isAlive()) {
                    curTar.die();
                    party.remove(curTar);
                }
                if (monsters.isEmpty()) {
                    break;
                }
            }

            br();
            printPartyStatus();
        } while (!party.isEmpty() && !monsters.isEmpty());
        br();
        if (monsters.isEmpty()) {
            System.out.println("敵を全て倒した" + party.getFirst().getName() + "達は勝利した!");
        } else if (party.isEmpty()) {
            System.out.println("味方パーティは全滅してしまった…");
        }

        System.out.println("---味方パーティ最終ステータス---");
        for (Character character : party) {
            character.showStatus();

            String isAlive;
            if (character.isAlive()) {
                isAlive = "生存";
            } else {
                isAlive = "戦闘不能";
            }
            System.out.println("生存状況：" + isAlive);
        }

        br();
        System.out.println("---敵グループ最終ステータス---");
        for (Monster monster : monsters) {
            monster.showStatus();

            String isAlive;
            if (monster.isAlive()) {
                isAlive = "生存";
            } else {
                isAlive = "討伐済み";
            }
            System.out.println("生存状況：" + isAlive);
        }
        if (monsters.isEmpty()) {
            System.out.println("殲　滅　！");
        }
    }
    private static Monster choiceEnemy() {
        switch ((int)(Math.random() * 3)) {
            case 0:
                return new Matango(45, (char) ('A' + matangoCnt++));
            case 1:
                return new Goblin(50, (char) ('A' + goblinCnt++));
            case 2:
                return new Slime(40, (char) ('A' + slimeCnt++));
            default:
                throw new IllegalArgumentException();
        }
    }

    private static Monster choiceTarget(Character curChar) throws InterruptedException {
        do {
            printEnemyStatus();
            try {
                int choice = readInt("[" + curChar.getName() + "]が攻撃するモンスターを選ぶんだドン(半角数字で頼む) >> ");
                return monsters.get(choice - 1);
            } catch (IndexOutOfBoundsException e) {
                System.err.println("存在するやつで頼む。");
                Thread.sleep(100);

            }
        } while (true);
    }

    private static int readInt(String message) throws InterruptedException {
        do {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print(message);
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.err.println("あー入出力エラー。おわりやね。");
                Thread.sleep(100);
            } catch (NumberFormatException e) {
                System.err.println("マジ半角数字で頼む。");
                Thread.sleep(100);
            }
        } while (true);
    }

    private static void printPartyStatus() {
        System.out.println("---現在生存しているパーティーメンバー---");
        int index = 1;
        for (Character character : party) {
            if (character.isAlive()) {
                System.out.println(character.getName() + " HP: " + character.getHp());
                index++;
            }
        }
        if (index == 1) {
            System.out.println("全てのモンスターは討伐されました！");
        }
        System.out.println("--------------------------------");
    }
    private static void printEnemyStatus() {
        System.out.println("---現在生存しているモンスター---");
        int index = 1;
        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                System.out.println(index + ": " + monster.getName() + monster.getSuffix() + " HP: " + monster.getHp());
                index++;
            }
        }
        if (index == 1) {
            System.out.println("全てのモンスターは討伐されました！");
        }
        System.out.println("----------------------------");
    }

    private static SuperHero evoHero(Hero hero) {
        SuperHero superHero = new SuperHero(hero);
        party.set(party.indexOf(hero), superHero);
        return superHero;
    }
    private static void br() {
        System.out.println();
    }
    private static void br(int cnt) {
        for(int i = 0; i < cnt; i++) {
            System.out.println();
        }
    }
}
