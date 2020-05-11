package sopra.formation.model;

public class Views {
	public static class ViewCommon {}
	
	public static class ViewEvaluation extends ViewCommon {}
	
	public static class ViewPersonne extends ViewCommon {}
	
	public static class ViewStagiaire extends ViewPersonne {}
	
	public static class ViewStagiaireDetail extends ViewStagiaire {}
	
	public static class ViewFormateur extends ViewPersonne {}
	
	public static class ViewFiliere extends ViewCommon {}
	
	public static class ViewFiliereWithReferent extends ViewFiliere {}
}
