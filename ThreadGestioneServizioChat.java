����   1 r  ThreadGestioneServizioChat  java/lang/Object  java/lang/Runnable nMaxConnessioni I lista Ljava/util/List; 	Signature )Ljava/util/List<LThreadChatConnessioni;>; listaThreadConnessioni [Ljava/lang/Thread; me Ljava/lang/Thread; 
serverChat Ljava/net/ServerSocket; <init> (ILjava/util/List;)V -(ILjava/util/List<LThreadChatConnessioni;>;)V Code
     ()V	    	   	 
  java/lang/Thread	  !  
  #  $ (Ljava/lang/Runnable;)V	  &  
  ( )  start LineNumberTable LocalVariableTable this LThreadGestioneServizioChat; LocalVariableTypeTable run 1 java/net/ServerSocket
 0 3  4 (I)V	  6  	 8 : 9 java/lang/System ; < out Ljava/io/PrintStream; > Server attivo
 @ B A java/io/PrintStream C D println (Ljava/lang/String;)V F !Errore nella creazione del server
 0 H I J accept ()Ljava/net/Socket; L ThreadChatConnessioni
 K N  O 0(LThreadGestioneServizioChat;Ljava/net/Socket;)V Q S R java/util/List T U add (Ljava/lang/Object;)Z
 0 W X  close Z !Impossibile creare la connessione
 \ ^ ] javax/swing/JOptionPane _ ` showMessageDialog )(Ljava/awt/Component;Ljava/lang/Object;)V b java/io/IOException d java/lang/Exception continua Z e Ljava/io/IOException; i tempo Ljava/net/Socket; nuovaConnessione LThreadChatConnessioni; threadConnessione Ljava/lang/Exception; 
SourceFile ThreadGestioneServizioChat.java !            	 
                                   �     **� *� *,� *� �  *� Y*� "� %*� %� '�    *          	      "  )  +        * , -     *      * 	 
  .       * 	    /     K     �<*� 0Y'� 2� 5� 7=� ?� M� 7E� ?<� [=� =*� 5� GN� KY*-� M:*� � P W� Y� ":*�  S� '�*� ���*� 5� V� 
MY� [�     a * x { c  *   N             $  &   * " / # 7 $ B % N & Y ' a ( f " q * x + | , � / +   R    � , -    � e f   
 g h  , E i   7 / j k  B $ l m  Y  n   |  g o   p    q