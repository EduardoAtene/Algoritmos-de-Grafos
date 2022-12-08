package ForkLibary.Entity;

public abstract class ForkEntity {

    public static class Vertice {
        /*
         * Classe Responsável por Gerenciar os Vértices do Grafo.
         * 
         * Para Grafos Direcionados: A criaÃ§Ã£o
         * 
         * Observação:   A Aresta não trata Peso que não seja um valor número. Ou seja, para adjacência
         *              que contém o peso tratado e equivalente a uma string, VAI DAR MERDA.
         */
        private Object nome; // 1 :: oLÁ MUNDO
        private int enumeracao; // 1  Vertice
        private int posicao; // 0 Matriz
        private double peso; // Peso Do Vértice

        // Construtores
        public Vertice ( Object nome,int enumeracao ,int posicao) {
            this.nome = nome;
            this.enumeracao = enumeracao;
            this.posicao = posicao;
        }

        // Getters and Setters
        public Object getNome( ){
            return this.nome;
        }

        public int getEnumeracao( ){
            return this.enumeracao;
        }

        public int getPosicao( ){
            return this.posicao;
        }

        public double getPonderamento( ){
            return this.peso;
        }

        public void setPonderamento(Double peso ){
            this.peso = peso;
        }

    }


    public static class Aresta {
        /*
         * Classe Responsável por Gerenciar as Arestas do Grafo.
         * 
         * Para Grafos Direcionados: A criação
         * 
         * Observação:   A Aresta não trata Peso que não seja um valor número. Ou seja, para adjacência
         *              que contém o peso tratado e equivalente a uma string, VAI DAR MERDA.
         */
        private Object vertice_1, vertice_2;
        private Object nome; // 1 :: oLÁ MUNDO
        private Double peso;
        private int posicao; // List
        private Boolean direcionado = false; // Para Grafos Direcionados, É instan
        private ArestaDirecao ArestaDirecao;

        private static class ArestaDirecao {
            private Object vertice_Inicial, vertice_Final;

            public ArestaDirecao ( Object vertice_Inicial, Object vertice_Final) {
                this.vertice_Inicial = vertice_Inicial;
                this.vertice_Final = vertice_Final;
            }
            
            public Object getVerticeInicial( ){ 
                return this.vertice_Inicial; 
            }
            public Object getVerticeFinal( ){ 
                return this.vertice_Final; 
            }
            public void invertDirection( ){ 
                Object aux;
                
                aux = this.vertice_Inicial; 
                this.vertice_Inicial = this.vertice_Final; 
                this.vertice_Final = aux ; 
            }
        }

        // Construtores
        public Aresta ( Object vertice_1, Object vertice_2, Double peso) {
            this.vertice_1 = vertice_1;
            this.vertice_2 = vertice_2;
            this.peso = peso; 
        }

        public Aresta ( Object vertice_1, Object vertice_2, Double peso, Boolean direcionado) {
            this(vertice_1,vertice_2,peso);
            this.direcionado = direcionado;
            this.ArestaDirecao = new ArestaDirecao(vertice_1,vertice_2);
        }

        public Aresta ( Object vertice_1, Object vertice_2, Double peso, Boolean direcionado,int posicao) {
            this(vertice_1,vertice_2,peso);
            this.direcionado = direcionado;
            this.posicao = posicao;
            this.ArestaDirecao = new ArestaDirecao(vertice_1,vertice_2);
        }


        // Getters and Setters
        
        public void setPosicao(int posicao ){
            this.posicao = posicao;
        }


        public Object getVertice_1( ){
            return this.vertice_1;
        }

        public Object getVertice_2( ){
            return this.vertice_2;
        }

        public Double getPeso( ){ 
            return this.peso; 
        }
        
        public int getPosicao( ){
            return this.posicao;
        }

       
        public boolean isDirecionado( ){ 
            return this.direcionado;
        }
       
    }

    public Integer quantidadeVertices;
    public Integer quantidadeArestas;
    
    public Integer getQtdeVertices() {
    	return this.quantidadeVertices;
    }
    
   

}