public class Warehouse {
    private int totalGoodsNum;

    public Warehouse(int goodsNum) {
        this.totalGoodsNum = goodsNum;
    }

    public synchronized int sell(int countOfGoods) {
        if(countOfGoods>totalGoodsNum){
            totalGoodsNum = 0;
            return totalGoodsNum;
        }
        else{
            totalGoodsNum -= countOfGoods;
            return countOfGoods;
        }
    }
}
