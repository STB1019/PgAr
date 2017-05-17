public interface Interfaccia {

	public String stampaMedici(); //toString di tutti i medici presenti

	public void stampaOrari(int matricola); //Stama gli Orari del Medico inserito

	public void stampaAppuntamenti(int matricola); //Stampa tutti gli appuntamenti del Medico Inserito

	public String stampaPazienti(); //Stampa tutti i Pazienti inseriti nel Programma

	public void stampaAppuntamenti(int dataInizio, int dataFine); //Stampa gli appuntamenti su un certo intervallo

	public int stampaNumAppuntamenti(int matricola); //Stampa il numero di appuntamenti per un certo Medico

	//Ho inserito le funzionalità di base, così potete sbizzarrirvi nel creare gli altri Metodi

	// ATTENZIONE: Creare metodi di Modifica di tutti i parametri (se Possibile) del vostro Programma

}
