package maka.fantasy.predictor;

public class PlayerModel {
    public String PlayerName;
    public String TeamName;
    public String BattingPoints;
    public String BowlPoints;
    public String OtherPoints;
    public String TotalPoints;
    public String Match;

    public PlayerModel(String pplayerName,String pTeamName,
                       String pBattingPoints,String pBowlPoints, String pOtherPoints, String pTotal,String pMatch){

        PlayerName = pplayerName;
        TeamName=pTeamName;
       /* BattingPoints=pBattingPoints;
        BowlPoints=pBowlPoints;
        TotalPoints=pTotal;
        Match=pMatch;*/
    }
    public PlayerModel(){

    }

    public String getID() {
        return PlayerName;
    }


}
