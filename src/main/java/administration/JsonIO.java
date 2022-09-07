package administration;

public class JsonIO {
/*
    public String readJson(String filepath) {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public void writeAccountData(Account account){
        String accountFilepath = "R:\\Java\\Bankautomat\\account_data.json";
        Gson gson = new Gson();
        String content = readJson(accountFilepath);
        ArrayList<Account> allAccounts = gson.fromJson(content, new TypeToken<ArrayList<Account>>() {}.getType());
        List<Account> oldCard = allAccounts.stream().
                filter(account1 -> account.getId() == account1.getId()).
                toList();
        allAccounts.remove(oldCard.get(0));
        allAccounts.add(account);
        String jsonText = gson.toJson(allAccounts, new TypeToken<ArrayList<Card>>() {}.getType());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accountFilepath));
            writer.write(jsonText);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
 */
}
