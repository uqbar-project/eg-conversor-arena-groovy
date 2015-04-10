package org.uqbar.arena.examples.conversor

import java.awt.Color

import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.MainWindow
import org.uqbar.lacar.ui.model.Action
/**
 * Ejemplo de conversor de medidas con el framework Arena. Es una ventana que tiene como modelo una instancia
 * de la clase {@link Conversor}.
 * 
 * Muestra:
 * <ul>
 * <li>un textbox donde se ingresa el valor de entrada</li>
 * <li>un botón para ejecutar la conversión.</li>
 * <li>un label donde se muestra el resultado de la conversión.</li>
 * </ul>
 * 
 * IMPORTANTE: Correr esta clase con el siguiente argumento a la VM
 * -Djava.system.class.loader=com.uqbar.apo.APOClassLoader
 * 
 * @author npasserini
 * @author flbulgarelli - versión groovy
 */
class ConversorWindow extends MainWindow<Conversor> {

	/**
	 * COMO FUNCIONA EL PROYECTO
	 * 1) Convert to Maven project
	 * 2) maven install
	 * 3) Convert to groovy project
	 */
	ConversorWindow() {
		super(new Conversor())
		title = "Conversor de millas a kilómetros"
	}

	@Override
	void createContents(Panel mainPanel) {
		mainPanel.with {
			layout = new VerticalLayout()
			new Label(it).with { //
				text = "Ingrese la longitud en millas" //
			}
			new TextBox(it).with { //
				bindValueToProperty("millas") //
			}
			def button = new Button(it).with {
				caption = "Convertir a kilómetros"
			// OPCION 1
			// Se trabaja el onClick con un adapter
			// que mapea objeto receptor, mensaje como un string
			// desventaja: no tiene validación de tipos
			// es fácil escribir "conevrtir" y eso no se chequea
			// onClick(new MessageSend(this.modelObject, "convertir"))
			
			// OPCION 2
			// Se trabaja con una inner class
			// al estilo "Java". 
			// Desventaja: es muy verboso, y requiere indentar
			// bien para poder seguir el scope
			// onClick(new Action() { 
			//	void execute() { 
			//		this.modelObject.convertir() 
			//	}
			//})
			// OPCION 3, generamos un closure y lo casteamos a una action
			// que implementa un solo método: execute() en este caso
			onClick({ this.modelObject.convertir() } as Action)
			}
			new Label(it).with {
				background = Color.ORANGE
				bindValueToProperty("kilometros")
			}
			new Label(it).with {  text = " kilómetros"  }
		}

		/** Opción 2 */
		/**
		 title = "Conversor de millas a kilómetros"
		 this.setTitle("Conversor de millas a kilómetros (Groovy)")
		 mainPanel.setLayout(new VerticalLayout())
		 new Label(mainPanel).setText("Ingrese la longitud en millas")
		 new TextBox(mainPanel).bindValueToProperty("millas")
		 new Button(mainPanel)
		 	.setCaption("Convertir a kilómetros en Groovy")
		 	.onClick(new MessageSend(this.getModelObject(), "convertir"))
		 new Label(mainPanel)
		 	.setBackground(Color.ORANGE)
		 	.bindValueToProperty("kilometros")
		 new Label(mainPanel).setText(" kilómetros")
		 */

	}

	static void main(String[] args) {
		new ConversorWindow().startApplication()
	}

}
