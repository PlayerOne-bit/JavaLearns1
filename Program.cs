using System;

public class Program
{
    public static void Main()
    {
        FourthWarmUp();
    }
    public static void FirstWarmUp()
    {
        Console.Write("Enter time in seconds: ");
        int totalSeconds = Convert.ToInt32(Console.ReadLine());
        int totalMinutes = totalSeconds / 60;
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        int seconds = totalSeconds % 60;
        Console.WriteLine($"Output: {hours}:{minutes}:{seconds}");
    }
    public static void SecondWarmUp()
    {
        Console.Write("Enter total amount of milk produced in the morning: ");
        int amountOfMilk = Convert.ToInt32(Console.ReadLine());

        double litersOfMilk = amountOfMilk/3.78;
        Console.WriteLine($"Liters of Milk: {litersOfMilk.ToString("F2")}L");

        int roundedLitersOfMilk =(int) Math.Round(litersOfMilk);
        Console.WriteLine($"The number of milk cartons needed to hold milk: {roundedLitersOfMilk}");

        double costOfProducingMilk = roundedLitersOfMilk * 0.36;
        Console.WriteLine($"The cost of the producing milk: {costOfProducingMilk.ToString("F2")}");

        double profitForProducingMilk = roundedLitersOfMilk*0.27;
        Console.WriteLine($"The profit for producing milk: {profitForProducingMilk.ToString("F2")}");
    }
    public static void ThirdWarmUp()
    {
        Console.Write("Enter month: ");
        string month = Console.ReadLine();
        Console.Write("Enter day: ");
        int day = Convert.ToInt32(Console.ReadLine());

        string[] months = {"","January", "February","March","April","May","June","July","August","September","November","December"};

        bool
            isAries =
            (month == months[3] && day >= 21 && day <= 31) ||
            (month == months[4] && day >= 1 && day <= 19),

            isTaurus =
            (month == months[4] && day >= 20 && day <= 30) ||
            (month == months[5] && day >= 1 && day <= 20),

            isGemini =
            (month == months[5] && day >= 21 && day <= 31) ||
            (month == months[6] && day >= 1 && day <= 20),

            isCancer =
            (month == months[6] && day >= 21 && day <= 30) ||
            (month == months[7] && day >= 1 && day <= 22),

            isLeo =
            (month == months[7] && day >= 23 && day <= 31) ||
            (month == months[8] && day >= 1 && day <= 22),

            isVirgo =
            (month == months[8] && day >= 23 && day <= 31) ||
            (month == months[9] && day >= 1 && day <= 22),

            isLibra =
            (month == months[9] && day >= 23 && day <= 30) ||
            (month == months[10] && day >= 1 && day <= 22),

            isScorpio =
            (month == months[10] && day >= 23 && day <= 31) ||
            (month == months[11] && day >= 1 && day <= 21),

            isSagittarius =
            (month == months[11] && day >= 22 && day <= 30) ||
            (month == months[12] && day >= 1 && day <= 21),

            isCapricorn =
            (month == months[12] && day >= 22 && day <= 31) ||
            (month == months[1] && day >= 1 && day <= 19),

            isAquarius =
            (month == months[1] && day >= 20 && day <= 31) ||
            (month == months[2] && day >= 1 && day <= 18),

            isPisces =
            (month == months[2] && day >= 19 && day <= 29) ||
            (month == months[3] && day >= 1 && day <= 20);

     

        if (isAries)
        {
            Console.WriteLine("You are Aries");
        }
        else if (isTaurus)
        {
            Console.WriteLine("You are Taurus");
        }
        else if (isGemini)
        {
            Console.WriteLine("You are Gemini");
        }
        else if (isCancer)
        {
            Console.WriteLine("You are Cancer");
        }
        else if (isLeo)
        {
            Console.WriteLine("You are Leo");
        }
        else if (isVirgo)
        {
            Console.WriteLine("You are Virgo");
        }
        else if (isLibra)
        {
            Console.WriteLine("You are Libra");
        }
        else if (isScorpio)
        {
            Console.WriteLine("You are Scorpio");
        }
        else if (isSagittarius)
        {
            Console.WriteLine("You are Sagittarius");
        }
        else if (isCapricorn)
        {
            Console.WriteLine("You are Capricorn");
        }
        else if (isAquarius)
        {
            Console.WriteLine("You are Aquarius");
        }
        else if (isPisces)
        {
            Console.WriteLine("You are Pisces");
        }
        else
        {
            Console.WriteLine("Invalid month or day");
        }
    }
    public  static void FourthWarmUp()
    {
        Knight player1 = new Knight(100);
        Knight player2 = new Knight(100);

        while (player1.getCharacter() == player2.getCharacter())
        {
            player1.setCharacter();
        }

        string[] states = {"BOTH BLOCK", "BOTH ATTACK", "ATTACK IDLE", "IDLE ATTACK", "ATTACK BLOCK", "BLOCK ATTACK"};
        Random rnd = new Random();
        do
        {
            int index = rnd.Next(0, states.Length);
            int damage1 = rnd.Next(1, 26);
            int damage2 = rnd.Next(1, 26);
            Console.WriteLine(states[index]);
            switch (states[index])
            {
                case "BOTH ATTACK":
                    player1.getsDamaged(damage2);
                    player2.getsDamaged(damage1);
                    break;
                case "IDLE ATTACK":
                    player1.getsDamaged(damage2);
                    break;
                case "ATTACK IDLE":
                    player2.getsDamaged(damage1);
                    break;
            }
            StateKnight(states[index]);
            Console.WriteLine($"""
                {player1.getCharacter()}: {player1.showHP()}
                {player2.getCharacter()}: {player2.showHP()}
                """);
            Thread.Sleep(1000);
            Console.Clear();
        } while (player1.isAlive() && player2.isAlive());
        if (!player1.isAlive())
        {
            Console.WriteLine($"{player2.getCharacter()} WINS!!!");
            StateKnight("DEAD WIN");
        }
        else if (!player2.isAlive())
        {
            Console.WriteLine($"{player1.getCharacter()} WINS!!!");
            StateKnight("WIN DEAD");
        }
        else if (!player1.isAlive() && !player2.isAlive())
        {
            Console.WriteLine("Both Dead");
            StateKnight("BOTH DEAD");
        }
        Console.WriteLine($"""
                {player1.getCharacter()}: {player1.showHP()}
                {player2.getCharacter()}: {player2.showHP()}
                """);
        

    }
    public static void StateKnight(string state)
    {
        switch (state)
        {
            case "BOTH BLOCK":
                Console.WriteLine(
                """
                     O  |   |  O
                     |--|   |--|
                    /\  |   | /\
                    """);
                break;
            case "BOTH ATTACK":
                Console.WriteLine(
                """
                     O   /  \   O
                     |--/    \--+
                    /\         /\
                    """);
                break;
            case "ATTACK BLOCK":
                Console.WriteLine(
                """
                     O   /   |  O
                     |--/    |--+
                    /\         /\
                    """);
                break;
            case "BLOCK ATTACK":
                Console.WriteLine(
                """
                     O  |  \   O
                     |--|   \--+
                    /\        /\
                    """);
                break;
            case "IDLE ATTACK":
                Console.WriteLine(
                """
                     O    \   O
                    /|\    \--+
                     /\      /\
                    """);
                break;
            case "ATTACK IDLE":
                Console.WriteLine(
                """
                    O   /    O
                    |--/    /|\
                    /\      /\
                    """);
                break;
            case "DEAD WIN":
                Console.WriteLine(
                """
                                \O/
                                 +
                     O--+--\     /\
                     """);
                break;
            case "WIN DEAD":
                Console.WriteLine(
                """
                     \O/
                      +
                     /\     /--+--O
                     """);
                break;
            case "BOTH DEAD":
                Console.WriteLine("O--+--\\  /--+--O");
                break;
        }
    }

}

class Knight
{
    private string character;
    private int HP;

    public Knight(int HP)
    {
        this.character = KnightNameGenerator();
        this.HP = HP;
    }

    public void setCharacter()
    {
        this.character=KnightNameGenerator();
    }
    public bool isAlive()
    {
        if (this.HP<=0)
        {
            this.HP=0;
        }

        return this.HP > 0;
    }
    public string getCharacter()
    {
        return this.character;
    }

    public int showHP()
    {
        return this.HP;
    }
    public void getsDamaged(int damage)
    {
        this.HP-=damage;
    }
    
    public string KnightNameGenerator()
    {
        Random rnd = new Random();
        string[]
            firstNames = { "Julliane Shayne", "Xypher", "Shawn", "Donovan", "Kuya Ed", "Nonilon", "Jasper", "Ian" },
            nickNames = { "Indian Boy", "Valo Pro", "Mobile Expert", "Gooner", "Father", "Bisaya", "Pookie Bear", "GOAT" };
        int index = rnd.Next(0, firstNames.Length);
        return $"{firstNames[index]} The {nickNames[index]}";
    }

    
}