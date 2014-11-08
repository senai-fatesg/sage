public class RecommenderIntro {

 public static void main(String[] args) throws Exception {
	DataModel model = new FileDataModel(
	    new File("ra.test"));
	/*
	 * fornece alguma no��o de qu�o semelhantes
	 * s�o dois usu�rios
	 */
	UserSimilarity similarity = new 
		PearsonCorrelationSimilarity(model);
	/*
	 * define a no��o de um grupo de usu�rios
	 * que s�o mais semelhantes a um determinado usu�rio
	 */
	UserNeighborhood neighborhood = 
		new NearestNUserNeighborhood(2, similarity, model);
	/*
	 * puxa todos estes componentes juntos para
	 * recomendar itens para os usu�rios.
	 */
	Recommender recommender = 
			new GenericUserBasedRecommender(model, 
					neighborhood, similarity); 
	
	for (int i = 1; i <= 69878; i++) {
	  /*
	   * parametro (x,y) onde x � o usuario
	   * e y � a quantidade de produtos a ser recomendado � ele
	   */
	List<RecommendedItem> recommendations = 
			recommender.recommend(i, 2);
	/*
	 * Imprime a lista de itens recomendados
	 */
	for (RecommendedItem recommendacao : recommendations) {
		System.out.println("Usuario "+ i +" "+ recommendacao);
		 }
	  }
   }
}