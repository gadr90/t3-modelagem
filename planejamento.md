## T3 Modelagem
### Utils
PlayerCoordsInTile(index:int, tileWidth:int) {
	new Point(x = i%w, y = Math.floor(i/w))
}

### Tamanho do mapa
Grid de 13x13

### Estrutura dos Componentes
- Janela principal extends JFrame
 - Dois JPanel - center e east
	centerPanel.setSize(1000, 700);
	eastPanel.setSize(200, 700);
  this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);