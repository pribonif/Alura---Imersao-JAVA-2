import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        // Pegar os dados do IMDB, acessando via http e buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // o cliente solicita, e ler como string e coloca numa variavel de resposta
        String body = response.body(); // pegando o corpo de response do IMDB
        //System.out.println(body);

        // extrair do body só os dados que nos interessam (título, poster(imagem), classificação)
        // criar um objeto pra poder chamar o parse
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
       // System.out.println(listaDeFilmes.size()); // pra saber o tamanho da lista
       // System.out.println(listaDeFilmes.get(0)); // pra exibir o primeiro tem

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m\u001b[44mTítulo:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[1mURL da imagem:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[1m\u001b[3mAvaliação:\u001b[m " + filme.get("imDbRating"));
            System.out.println();
            
            float avaliacao = Float.parseFloat(filme.get("imDbRating"));
            int estrelas = (int) avaliacao;

             /*
            for (int n = 1; n <= estrelas; n++) {
                System.out.print("⭐️");
            }
            */

            if (avaliacao >= 7.0 && avaliacao <= 8.9) {
                System.out.println("⭐️⭐️⭐️⭐️");
            }
            else if (avaliacao >= 9 && avaliacao <=10) {
                System.out.println("⭐️⭐️⭐️⭐️⭐️");
            }

            System.out.println();


        }
          
        }
    }

