CREATE TABLE profesores (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  apellido VARCHAR(120) NOT NULL,
  email VARCHAR(180) UNIQUE
);

CREATE TABLE profesor_perfiles (
  profesor_id BIGINT PRIMARY KEY,
  bio TEXT,
  oficina VARCHAR(80),
  foto_url TEXT,
  CONSTRAINT fk_perfil_profesor
    FOREIGN KEY (profesor_id) REFERENCES profesores(id) ON DELETE CASCADE
);

CREATE TABLE materias (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL UNIQUE
);

-- Many-to-Many profesor <-> materia
CREATE TABLE profesor_materia (
  profesor_id BIGINT NOT NULL,
  materia_id BIGINT NOT NULL,
  PRIMARY KEY (profesor_id, materia_id),
  CONSTRAINT fk_pm_profesor FOREIGN KEY (profesor_id) REFERENCES profesores(id) ON DELETE CASCADE,
  CONSTRAINT fk_pm_materia FOREIGN KEY (materia_id) REFERENCES materias(id) ON DELETE RESTRICT
);

CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(180) NOT NULL UNIQUE,
  nombre VARCHAR(120) NOT NULL
);

CREATE TABLE calificaciones (
  id BIGSERIAL PRIMARY KEY,
  profesor_id BIGINT NOT NULL,
  usuario_id BIGINT NOT NULL,
  puntaje INT NOT NULL,
  comentario TEXT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  CONSTRAINT fk_calif_profesor FOREIGN KEY (profesor_id) REFERENCES profesores(id) ON DELETE CASCADE,
  CONSTRAINT fk_calif_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
  CONSTRAINT chk_puntaje CHECK (puntaje BETWEEN 1 AND 5)
);

-- Para chequear rápido la última calificación (cooldown 3 días)
CREATE INDEX idx_calif_profesor_usuario_created
  ON calificaciones (profesor_id, usuario_id, created_at DESC);

-- Para cálculos por profesor
CREATE INDEX idx_calif_profesor ON calificaciones (profesor_id);
