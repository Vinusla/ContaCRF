-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 28-Nov-2015 às 22:28
-- Versão do servidor: 10.1.8-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
CREATE DATABASE contacrf;
--
-- Database: `contacrf`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agencia`
--

CREATE TABLE `agencia` (
  `num_Agencia` varchar(10) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `num_Conta` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `conta`
--

CREATE TABLE `conta` (
  `num_conta` varchar(10) NOT NULL,
  `saldo` float(10,2) NOT NULL,
  `senha` varchar(6) NOT NULL,
  `cpf_pesF` varchar(11) NOT NULL,
  `ativo` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `id` int(2) NOT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `numero` varchar(5) DEFAULT NULL,
  `bairro` varchar(20) DEFAULT NULL,
  `cep` varchar(10) NOT NULL,
  `complemento` varchar(255) NOT NULL,
  `estado` char(2) NOT NULL,
  `cidade` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoafisica`
--

CREATE TABLE `pessoafisica` (
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(11) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `dataNasc` varchar(12) NOT NULL,
  `rg` varchar(7) NOT NULL,
  `id_end` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo_transacao`
--

CREATE TABLE `tipo_transacao` (
  `id_tipo_trans` int(2) NOT NULL,
  `nome_trans` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tipo_transacao`
--

INSERT INTO `tipo_transacao` (`id_tipo_trans`, `nome_trans`) VALUES
(1, 'saque'),
(2, 'deposito'),
(3, 'transferencia');

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacao`
--

CREATE TABLE `transacao` (
  `id_trans` int(2) NOT NULL,
  `tipo_trans` int(2) NOT NULL,
  `num_conta` int(2) NOT NULL,
  `valor` float(5,2) NOT NULL,
  `dataTrans` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agencia`
--
ALTER TABLE `agencia`
  ADD PRIMARY KEY (`num_Agencia`),
  ADD KEY `num_Conta` (`num_Conta`);

--
-- Indexes for table `conta`
--
ALTER TABLE `conta`
  ADD PRIMARY KEY (`num_conta`),
  ADD KEY `cpf_pesF` (`cpf_pesF`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pessoafisica`
--
ALTER TABLE `pessoafisica`
  ADD PRIMARY KEY (`cpf`),
  ADD KEY `id_end` (`id_end`);

--
-- Indexes for table `tipo_transacao`
--
ALTER TABLE `tipo_transacao`
  ADD PRIMARY KEY (`id_tipo_trans`);

--
-- Indexes for table `transacao`
--
ALTER TABLE `transacao`
  ADD PRIMARY KEY (`id_trans`),
  ADD KEY `tipo_trans` (`tipo_trans`),
  ADD KEY `tipo_trans_2` (`tipo_trans`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `agencia`
--
ALTER TABLE `agencia`
  ADD CONSTRAINT `agencia_ibfk_1` FOREIGN KEY (`num_Conta`) REFERENCES `conta` (`num_Conta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `conta`
--
ALTER TABLE `conta`
  ADD CONSTRAINT `conta_ibfk_1` FOREIGN KEY (`cpf_pesF`) REFERENCES `pessoafisica` (`cpf`);

--
-- Limitadores para a tabela `pessoafisica`
--
ALTER TABLE `pessoafisica`
  ADD CONSTRAINT `pessoafisica_ibfk_1` FOREIGN KEY (`id_end`) REFERENCES `endereco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `transacao`
--
ALTER TABLE `transacao`
  ADD CONSTRAINT `transacao_ibfk_1` FOREIGN KEY (`tipo_trans`) REFERENCES `tipo_transacao` (`id_tipo_trans`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
