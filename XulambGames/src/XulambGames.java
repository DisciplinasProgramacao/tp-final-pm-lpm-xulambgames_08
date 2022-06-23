import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;


public class XulambGames {
    private ArrayList<Compra> compras;
    private ArrayList<Cliente> clientes;
    private ArrayList<Jogos> jogos;
    Console console;

    private static XulambGames instance;

    private XulambGames(){
        this.jogos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.compras = new ArrayList<>();
        this.console = System.console();
    }


    public static XulambGames getInstance(){
        if (instance == null){
            instance = new XulambGames();
        } 
        return instance;
    }

    public void valorMensalVendido(){
        Console scanner = this.console;
        console.flush();
        int mes = -1;

        while (mes != 0){
            try {
                System.out.println("Qual mes voce gostaria de verificar?");
                System.out.println("0 - Voltar menu principal");
                System.out.println("Mes: ");
                String mesS = scanner.readLine();
                mes = Integer.parseInt(mesS);
                float valorMensal = 0;
                if (mes > 0 && mes <= 12){
                    for (Compra compra : this.compras){
                        if (compra.verificarMes(mes)){
                            valorMensal += compra.valorCompra();
                        }
                    }
                    System.out.println("Valor mensal foi de R$" + valorMensal);
                    mes = 0;
                }else if(mes != 0){
                    System.out.println("Valor Invalido, mes deve estar entre 1 e 12");
                }
            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, mes deve estar entre 1 e 12");
            }catch(NullPointerException e){
                System.out.println("Nao existem compras cadastradas");
                break;
            }catch(NumberFormatException e){
                System.out.println("Input invalido"); 
            }catch(Exception e){
                System.out.println(e);
            }            
        }
        scanner.flush();
    }

    public void valorMedioCompras(){
        float valorMedio = 0;
        int quantidadeCompras = 0;
        for (Compra compra : this.compras){
            valorMedio += compra.valorCompra();
            quantidadeCompras++;
        }
        if (quantidadeCompras == 0){
            System.out.println("Sem compras registradas");
        }else{
            System.out.println("Valor medio das compras R$" + (valorMedio / quantidadeCompras));
        }
    }

    public void jogosExtremos(){
        try{
            Jogos maisVendido = null;
            Jogos menosVendido = null;
            int qntMenosVendido = -1;
            int qntMaisVendido = -1;
            int qntJogo;
            for(Jogos jogo : this.jogos){
                qntJogo = jogo.getQuantidadeVendida();
                if (qntMaisVendido == -1 && qntMenosVendido == -1){
                    maisVendido = menosVendido = jogo;
                    qntMaisVendido = qntMenosVendido = qntJogo;
                }
                else if (qntMaisVendido < qntJogo){
                    qntMaisVendido = qntJogo;
                    maisVendido = jogo;
                }else if(qntMenosVendido > qntJogo){
                    qntMenosVendido = qntJogo;
                    menosVendido = jogo;
                }
            }
            if (maisVendido == null){
                    System.out.println("Nenhum jogo encontrado");
            }
            StringBuilder jogosExtremos = new StringBuilder("Jogo mais vendido:\n");
            jogosExtremos.append(maisVendido);
            jogosExtremos.append("\nJogo menos vendido:\n");
            jogosExtremos.append(menosVendido); 
            System.out.println(jogosExtremos);
        }catch(NullPointerException e){
            System.out.println("Nao possuem jogos cadastrados!");
        }
        
    }

    public void adicionarCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

    public void adicionarCompra(Compra compra){
        this.compras.add(compra);
    }

    public void adicionarJogo(Jogos jogo){ 
        this.jogos.add(jogo);  
    }

    public void salvarDados() {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream("baseDados.bin");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            try{
                for(Cliente cliente : this.clientes) {
                    objectOutputStream.writeObject(cliente);
                }
            }catch(NullPointerException e){
                System.out.println("Sem clientes para salvar!");
            }catch(Exception e){
                System.out.println(e);
            }

            try{
                for(Compra compra : this.compras) {
                    objectOutputStream.writeObject(compra);
                }
            }catch(NullPointerException e){
                System.out.println("Sem compras para salvar!");
            }catch(Exception e){
                System.out.println(e);
            }

            try{
                for(Jogos jogo : this.jogos) {
                    objectOutputStream.writeObject(jogo);
                }
            }catch(NullPointerException e){
                System.out.println("Sem jogos para salvar!");
            }catch(Exception e){
                System.out.println(e);
            }       
            
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sistema finalizado com sucesso!");
    }

    public void lerDados() {
        try {
            FileInputStream fileInputStream = new FileInputStream("baseDados.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object object;
            while ((object = objectInputStream.readObject()) != null) {
                if (object instanceof Cliente cliente) {
                    this.clientes.add(cliente);
                } else if (object instanceof Jogos jogo) {
                    this.jogos.add(jogo);
                } else if (object instanceof Compra compra) {
                    this.compras.add(compra);
                }
            }

            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Bem-vindo a nova XulambGames!");
        } catch (EOFException e) {
            System.out.println("Sistema esta pronto para iniciar!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cadastrarCliente() {
        Console scanner = this.console;
        scanner.flush();
        System.out.print("Nome Cliente: ");
        String nomeCliente = scanner.readLine();

        if (this.buscarCliente(nomeCliente) != null){
            System.out.println("Cliente ja existe no sistema. Voltando para menu!");
            return;
        }

        System.out.print("Nome de usuario: ");
        String nomeUsuario = scanner.readLine();

        System.out.print("Senha: ");
        String senha = scanner.readLine();

        TipoCliente tipoCliente = null;
        while (tipoCliente == null){
            try {
                System.out.println("Tipos de cliente:");
                System.out.println("1 - Cadastrado");
                System.out.println("2 - Empolgado");
                System.out.println("3 - Fanatico");
                System.out.print("Opcao: ");
                String opcaoStr = scanner.readLine();
                int opcao = Integer.parseInt(opcaoStr);
                switch (opcao) {
                    case 1:
                        tipoCliente = TipoCliente.CADASTRADOS;
                        break;
                    case 2:
                        tipoCliente = TipoCliente.EMPOLGADOS;
                        break;
                    case 3:
                        tipoCliente = TipoCliente.FANATICOS;
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, tente novamente");
                scanner.readLine();
            }catch(NumberFormatException e){
                System.out.println("Input invalido"); 
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        Cliente cliente = new Cliente(nomeCliente, nomeUsuario, senha, tipoCliente);        

        this.clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
        scanner.flush();
    }

    private void cadastrarJogos() {
        // BufferedInputStream scanner = new BufferedInputStream(System.in);
        Console scanner = this.console;
        scanner.flush();
        // Scanner scanner = new Scanner(System.in);
        System.out.print("Nome Jogo: ");
        String nomeJogo = scanner.readLine();
        // System.in.read(new byte[System.in.available()]);

        if (this.buscarJogo(nomeJogo) != null){
            System.out.println("Jogo ja existe no sistema. Voltando para menu!");
            return;
        }
        
        // System.in.close();
        // scanner = new Scanner(System.in);

        float precoBase = 0;
        while (precoBase == 0){
            try{
                System.out.print("Preco base: R$");
                String preco = scanner.readLine();
                precoBase = Float.parseFloat(preco);
            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, tente novamente");
                scanner.readLine();
                precoBase = 0;
            }catch(NumberFormatException e){
                System.out.println("Input invalido");
            
            }catch(Exception e){
                System.out.println(e);
            }
        }

        Categoria categoria = null;
        while (categoria == null){
            try {
                
                System.out.println("Tipos de categoria:");
                System.out.println("1 - Lancamentos");
                System.out.println("2 - Premium");
                System.out.println("3 - Regulares");
                System.out.println("4 - Promocoes");
                System.out.print("Opcao: ");
                // String opcaoString = scanner.nextInt();
                String opcao2 = scanner.readLine();
                int opcao = Integer.parseInt(opcao2);
                // System.out.println(opcao);
                // int opcao2 = scanner.nextInt();
                // categoria = Categoria.LANCAMENTOS;

                switch (opcao) {
                    case 1:
                        categoria = Categoria.LANCAMENTOS;
                        break;
                    case 2:
                        categoria = Categoria.PREMIUM;
                        break;
                    case 3:
                        categoria = Categoria.REGULARES;
                        break;
                    case 4:
                        categoria = Categoria.PROMOCOES;
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, tente novamente");
                scanner.readLine();
            }catch(NoSuchElementException e){
                System.out.println("Elemento nao encontrado");
                categoria = Categoria.PROMOCOES;
                scanner.readLine();
            }catch(NumberFormatException e){
                System.out.println("Input invalido");
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        Jogos jogo = new Jogos(precoBase, categoria, nomeJogo);        

        this.jogos.add(jogo);
        System.out.println("Jogo cadastrado com sucesso!");
        scanner.flush();
    }

    private void cadastrarCompra() {
        Console scanner = this.console;
        scanner.flush();
        System.out.print("Nome Jogo: ");
        String nomeJogo = scanner.readLine();
        Jogos jogo = this.buscarJogo(nomeJogo); 
        
        if (jogo == null){
            System.out.println("Jogo nao existe no sistema. Voltando para menu!");
            return;
        }
        
        // scanner.next();
        System.out.print("Nome Cliente: ");
        String nomeCliente = scanner.readLine();
        Cliente cliente = this.buscarCliente(nomeCliente);
        
        if (cliente == null){
            System.out.println("Cliente nao existe no sistema. Voltando para menu!");
            return;
        }

        Data data = this.selecionarData();

        Compra compra = new Compra(data, jogo, cliente);

        this.adicionarCompra(compra);
        System.out.println("Compra adicionada com sucesso!");
    }

    private Data selecionarData() {
        Console scanner = this.console;
        scanner.flush();
        int diaI = 0, mesI = 0, anoI = 0;
        String dia, mes, ano;
        while(diaI <= 0 || mesI <= 0 || anoI <= 0){
            try{
                System.out.print("Diga o ano da Compra: ");
                ano = scanner.readLine();
                anoI = Integer.parseInt(ano);

                System.out.print("Diga o mes da Compra: ");
                mes = scanner.readLine();
                mesI = Integer.parseInt(mes);

                System.out.print("Diga o dia da Compra: ");
                dia = scanner.readLine();
                diaI = Integer.parseInt(dia);

            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, tente novamente");
            }catch(NumberFormatException e){
                System.out.println("Input invalido");
            }catch(Exception e){
                System.out.println(e);
            }
        }
        scanner.flush();
        return new Data(diaI, mesI, anoI);
    }


    private Jogos buscarJogo(String nomeJogo) {
        if (! this.jogos.isEmpty()){
            for(Jogos jogo : this.jogos){
                if (jogo.getNome().compareTo(nomeJogo) == 0){
                    return jogo;
                }
            }
        }        
        return null;
    }


    private Cliente buscarCliente(String nomeCliente) {

        try{
            for (Cliente cliente : this.clientes){
                if (cliente.getNome().compareTo(nomeCliente) == 0){
                    return cliente;
                }
            }
        }catch(NullPointerException e){
            System.out.println("Sem clientes cadastrados");
            return null;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    

    private void menu(){
        this.lerDados();
        Scanner scanner = new Scanner(System.in);
        int itemMenu = -1;

        while (itemMenu != 0){
            
            System.out.println("\n-------------------------\nBem vindo a Xulamb Games!");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar jogo");
            System.out.println("3 - Cadastrar compra");
            System.out.println("4 - Jogos Extremos");
            System.out.println("5 - Valor mensal vendido");
            System.out.println("6 - Valor medio compras");
            System.out.println("7 - Jogos Extremos");
            System.out.println("8 - Historico de compras");
            System.out.print("0 - Sair");
            System.out.print("\n\nOpcao: ");
            System.out.flush();
            try{
                itemMenu = scanner.nextInt();
                // System.in.read(new byte[System.in.available()]);
                // System.in. skip skipNBytes(System.in.available());
                switch (itemMenu) {
                    case 1:
                        this.cadastrarCliente();
                        break;
                    case 2:
                        this.cadastrarJogos();                        
                        break;
                    case 3:
                        this.cadastrarCompra();
                        break;
                    case 4:
                        this.jogosExtremos();
                        break;
                    case 5:
                        this.valorMensalVendido();
                        break;
                    case 6:
                        this.valorMedioCompras();
                        break;
                    case 7:
                        this.jogosExtremos();
                        break;
                    case 8:
                        this.historicoCompras();
                        break;                
                    case 0:
                        System.out.println("\n----------------------------\nObrigado pela preferencia!");
                        break;                
                    default:
                        System.out.println("Opcao invalida");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Valor Invalido, tente novamente");
                scanner.next();
            }catch(Exception e){
                System.out.println(e);
            }
        }  
        scanner.close();
        this.salvarDados();
    }

    private void historicoCompras() {
        StringBuilder historico = new StringBuilder("------------------------\nHistorico de compras:\n");
        for(Compra compra: this.compras){
            historico.append("\n" + compra.relatorio());
            historico.append("\n------------------------");
        }
        historico.append("\n------------------------\n");
        System.out.println(historico);
    }


    public static void main(String[] args){
        XulambGames xulambGames = XulambGames.getInstance();
        xulambGames.menu();
    }
}
