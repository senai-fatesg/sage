public class RecommenderIntro {

 public static void main(String[] args) throws Exception {
	DataModel model = new FileDataModel(
	    new File("ra.test"));
	/*
	 * fornece alguma noção de quão semelhantes
	 * são dois usuários
	 */
	UserSimilarity similarity = new 
		PearsonCorrelationSimilarity(model);
	/*
	 * define a noção de um grupo de usuários
	 * que são mais semelhantes a um determinado usuário
	 */
	UserNeighborhood neighborhood = 
		new NearestNUserNeighborhood(2, similarity, model);
	/*
	 * puxa todos estes componentes juntos para
	 * recomendar itens para os usuários.
	 */
	Recommender recommender = 
			new GenericUserBasedRecommender(model, 
					neighborhood, similarity); 
	
	for (int i = 1; i <= 69878; i++) {
	  /*
	   * parametro (x,y) onde x é o usuario
	   * e y é a quantidade de produtos a ser recomendado à ele
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