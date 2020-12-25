package sample;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
interface BlockchainInterface {
    public static String applySha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isHexadecimal(String s){
        String regex = "[0-9a-fA-F]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);

        return !m.find();
    }

    public static int base2Log(int N){
        if (N <= 0){
            throw new IllegalArgumentException();
        } else {
            return 31 - Integer.numberOfLeadingZeros(N);
        }
    }
}

class MerkleTree implements BlockchainInterface{
    private List<String> Transactions = new ArrayList<String>();
    private List<ArrayList<String>> MT = new ArrayList<ArrayList<String>>();

    private int sizeMT;
    private String rootMT;
    private int heightMT;

    MerkleTree(){
        sizeMT = 0;
        heightMT = 0;
        rootMT = "";
    }

    MerkleTree(List<String> Transactions){
        sizeMT = Transactions.size();

        if (sizeMT == 0){
            rootMT = "";
            heightMT = 0;
            return;
        } else {
            this.Transactions = Transactions;
        }

        MT = buildMT(Transactions);
        heightMT = MT.size() - 1;
        rootMT = MT.get(heightMT).get(0);
    }

    public String getRoot() { return rootMT; }
    public int getHeight() { return heightMT; }
    public int getSize() { return sizeMT; }
    public List<ArrayList<String>> getMT() { return MT; }

    public int getIndex(String transaction){ return MT.get(0).indexOf(transaction); }

    private List<ArrayList<String>> buildMT(List<String> Transactions){
        ArrayList<String> Level = new ArrayList<String>();

        for (int i = 0; i < sizeMT; i++){ Level.add(BlockchainInterface.applySha256(Transactions.get(i))); }

        MT.add(Level);

        int heightMT = 0;
        int sizeLevel = Level.size();
        while (sizeLevel > 1){
            Level = new ArrayList<String>();

            int index = 0;
            while (index < sizeLevel){
                String left = MT.get(heightMT).get(index);
                index++;

                String right = left;
                if (index != sizeLevel) {
                    right = MT.get(heightMT).get(index);
                }

                Level.add(BlockchainInterface.applySha256(left + right));
                index++;
            }
            MT.add(Level);
            heightMT++;
            sizeLevel = Level.size();
        }
        return MT;
    }

    public void clear(){
        MT.clear();
        Transactions.clear();
        sizeMT = 0;
        heightMT = 0;
        rootMT = "";
    }
}

class Block implements BlockchainInterface {
    private String version, hash, previousHash, root;
    private int height, bits;
    private long timeStamp, nonce;

    public Block(String version, String previousHash, String root, int height){
        this.version = version;
        this.previousHash = previousHash;
        this.root = root;
        this.height = height;
        this.timeStamp = new Date().getTime();
        this.bits = bits;

        Random rand = new Random();
        this.bits = rand.nextInt(Integer.MAX_VALUE);
        this.nonce = rand.nextInt(Integer.MAX_VALUE);

        this.hash = calculateHash();
    }

    public String calculateHash(){
         return BlockchainInterface.applySha256(version+previousHash+root+Long.toString(timeStamp)+Integer.toString(bits)+Long.toString(nonce));
    }

    public String getHash(){ return hash; }
    public String getPreviousHash() { return previousHash; }

    @Override
    public String toString() {
        return "hash='" + hash + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", Merkle root='" + root + '\'' +
                ", height=" + height + "\n";
    }
}

class Blockchain{

    public List<Block> blockchain = new ArrayList<Block>();
    public List<String> Transactions = new ArrayList<String>();
    int nTransactions;

    Blockchain(){
        Random rand = new Random();
        nTransactions = 14;
        for (int n = 0; n < nTransactions; n++){
            Transactions.add(Integer.toString(rand.nextInt(1000)));
        }
        System.out.println("Transactions:\n" + Transactions + "\n");

        MerkleTree MT = new MerkleTree(Transactions);
        blockchain.add(new Block("0x20000000", "0", MT.getRoot(), 0));

        System.out.println("Merkle Tree:");
        System.out.println("Root: " + MT.getRoot());
        System.out.println("Height: " + MT.getHeight());

        for (int n = 0; n <= MT.getHeight(); n++){
            System.out.println("Level: " + n + " Number of Nodes: " + MT.getMT().get(n).size());
            System.out.println(MT.getMT().get(n));
        }
    }

    public boolean isValid(){
        for (int i = 1; i < blockchain.size(); i++){

            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i-1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())){
                System.out.println("Current " + i);
                return false;
            }

            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())){
                return false;
            }
        }
        return true;
    }

    public String toString() { return "Blockchain:\n" + blockchain; }
}

public class TestBlockChain {
    public static int N = 15, appendN = 5;

    public static void main(String[] args){

        Blockchain Bchain = new Blockchain();

        if (Bchain.isValid()){
            System.out.println("\n" + Bchain);
        } else {
            System.out.println("Blockchain invalid!");
        }
    }
}

 */